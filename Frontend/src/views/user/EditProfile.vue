<template>
  <div class="container">
    <div class="main-body">
      <div class="row">
        <div class="col-lg-4">
          <div class="card">
            <div class="card-body">
              <div class="d-flex flex-column align-items-center text-center">
                <img
                  src="https://bootdey.com/img/Content/avatar/avatar6.png"
                  alt="Admin"
                  class="rounded-circle p-1 bg-primary"
                  width="110"
                />
                <div class="mt-3">
                  <h4>John Doe</h4>
                  <p class="text-secondary mb-1">Full Stack Developer</p>
                  <p class="text-muted font-size-sm">
                    Bay Area, San Francisco, CA
                  </p>
                  <button class="btn btn-primary">Follow</button>
                  <button class="btn btn-outline-primary">Message</button>
                </div>
              </div>
              <hr class="my-4" />
            </div>
          </div>
        </div>
        <div class="col-lg-8">
          <div class="card">
            <div class="card-body">
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Full Name</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" v-model="fullName" />
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">dateOfBirth</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" v-model="dateOfBirth" />
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Phone</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" />
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Mobile</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" />
                </div>
              </div>
              <div class="row mb-3">
                <div class="col-sm-3">
                  <h6 class="mb-0">Address</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                  <input type="text" class="form-control" v-model="address" />
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-9 text-secondary">
                  <input
                    type="button"
                    class="btn btn-primary px-4"
                    value="Save Changes" @click="edit"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import useMixin from "../../mixins/common";
import store from "../../store/index";

export default defineComponent({
  setup() {
    const router = useRouter();
    const fullName = ref("");
    const dateOfBirth = ref("");
    const address = ref();
    const { post } = useMixin();
    const listuser =() => {
      router.push("/profile");
    }
    const edit = async () => {
      const dataEdit = {
        fullName: fullName.value,
        dateOfBirth: dateOfBirth.value,
        address: address.value,
      };
      const result = await post("/user/api/information", dataEdit);
      if (result.code === 200) {
        router.push("/profile");
      }
    };
    
    return {
        edit,
        listuser,
        fullName,
        dateOfBirth,
        address,
    };
  },
});
</script>

<style>
</style>