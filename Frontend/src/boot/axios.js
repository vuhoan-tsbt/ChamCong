import axios from 'axios';
import { ENV } from '../config.js';
import store from "../store/index";

const axiosInstance = axios.create({
    baseURL: ENV.URI_API,
    timeout: 150000,
    withCredentials: true,
    headers: {
      
    },
});

console.log(ENV.NODE_ENV);

const debug = ENV.NODE_ENV !== 'production';
// Add a request interceptor
axiosInstance.interceptors.request.use(
    (config) => {
      if (debug) {
        console.log(`REQUEST URL :${config.url}`);
      }
  
      if (!config.url) {
        return {
          headers: {},
          method: config.method,
          url: '',
        };
      }
  
      return config;
    },
    (error) => {
      if (debug) {
        console.log('REQUEST ERROR :');
        console.log(error);
      }
  
      return Promise.reject(error);
    },
);
// Add a response interceptor
axiosInstance.interceptors.response.use(
    (response) => {
      if (debug) {
        console.log('RESPONSE :');
        console.log(response.data);
      }
      return response;
    },
    (error) => {
      if (debug) {
        console.log('RESPONSE ERROR :');
        console.log(error);
      }
  
      return Promise.reject(error);
    },
  );
  
  export default axiosInstance;
  