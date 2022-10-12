import { getCurrentInstance, ref } from "vue";
import { ENV } from "../config";
import { RESPONSE } from "../helpers/constant";
import api from "../boot/axios";
import Objects from "../helpers/objects";
import ajv from "../boot/ajv";
import router from "../router";
import store from "../store";

const debug = ENV.NODE_ENV !== "production";

export default function useMixin() {
  const loader = ref({});
  const errors = ref({});
  const internalInstance = getCurrentInstance();
  const { $loading } = internalInstance.proxy;

  const showLoading = (
    fullScreen = true,
    container = null,
    onCancel = null
  ) => {
    loader.value = $loading.show({
      // Optional parameters
      container: fullScreen ? null : container,
      canCancel: onCancel !== null,
      onCancel,
    });
  };
  const hideLoading = () => {
    setTimeout(() => {
      loader.value.hide();
    }, 400);
  };
  const validateData = (schema, data) => {
    errors.value = {};
    const validate = ajv.compile(schema);
    const valid = validate(data);
    if (!valid) {
      const lst = {};
      validate.errors.forEach((obj) => {
        const dataPath = obj.instancePath.slice(1, obj.instancePath.length);
        if (!lst[dataPath]) {
          lst[dataPath] = obj.message;
        }
      });
      errors.value = lst;
      return false;
    }
    return true;
  };
  const reset = () => {
    errors.value = {};
  };
  const http = async (
    method,
    apiPath,
    data,
    showLoadingFlg = true,
    config = {}
  ) => {
    if (showLoadingFlg) {
      showLoading();
    }

    const defaultConfig = {
      url: apiPath,
      method,
    };

    if (method === "GET") {
      defaultConfig.params = data;
    } else {
      defaultConfig.data = data;
    }
    if(store.getters['common/getME']) {
      config.headers = {'Authorization': 'Bearer ' + store.getters['common/getME']?.token }
    }
    

    try {
      const request = Objects.mergeObjects(defaultConfig, config);
      const response = await api(request);
      if (response.data.code === RESPONSE.SUCCESS) {
        return response.data;
        // eslint-disable-next-line no-else-return
      } else if (response.data.code === RESPONSE.ERROR.UNAUTHORIZED) {
        store.dispatch("common/updateAdminUser", null);
        router.push("/login");
      } else if (response.data.code > 2 && response.data.code < 199) {
      } else {
      }
      return response.data;
    } catch (error) {
      // Error 😨
      if (error.response) {
        /*
         * The request was made and the server responded with a
         * status code that falls out of the range of 2xx
         */
        if (debug) {
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
        }
        return {
          code: error.response.status,
          message: error.response.data.message,
          payload: {},
        };
      }
      if (error.request) {
        /*
         * The request was made but no response was received, `error.request`
         * is an instance of XMLHttpRequest in the browser and an instance
         * of http.ClientRequest in Node.js
         */
        if (debug) {
          console.log(error.request);
        }
        return {
          code: 503,
          message:
            "The request was invalid or cannot be otherwise served. Please try again.",
          payload: {},
        };
      }
      // Something happened in setting up the request and triggered an Error
      if (debug) {
        console.log("Error", error.message);
      }
      return {
        code: 400,
        message:
          "The request had bad syntax or was inherently impossible to be satisfied.",
        payload: {},
      };
    } finally {
      if (showLoadingFlg) {
        hideLoading();
      }
    }
  };
  const post = async (apiPath, data, showLoadingFlg = true, config = {}) =>
    http("POST", apiPath, data, showLoadingFlg, config);

  const get = async (apiPath, params, showLoadingFlg = true, config = {}) =>
    http("GET", apiPath, params, showLoadingFlg, config);

  const put = async (apiPath, params, showLoadingFlg = true, config = {}) =>
    http("PUT", apiPath, params, showLoadingFlg, config);

  const del = async (apiPath, params, showLoadingFlg = true, config = {}) =>
    http("DELETE", apiPath, params, showLoadingFlg, config);

  return {
    // data errors
    errors,
    // methods
    post,
    get,
    put,
    del,
    showLoading,
    hideLoading,
    validateData,
    reset,
  };
}
