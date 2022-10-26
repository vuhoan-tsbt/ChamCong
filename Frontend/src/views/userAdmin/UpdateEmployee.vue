<template>
  <section class="h-100 h-custom gradient-custom-2">
    <div class="container py-5 h-100">
      <button type="button" class="btn btn-primary"  @click="listuser">Come Back</button>
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div
          class="card card-registration card-registration-2"
          style="border-radius: 15px"
        >
          <div class="p-5">
            <h3 class="fw-normal mb-5" style="color: #4835d4">
              From Update Staff
            </h3>
            <label class="form-label" for="form3Examplev4"
              ><h5>email</h5></label
            >
            <div class="mb-4 pb-2">
              <div class="form-outline">
                <input
                  type="text"
                  id="form3Examplev4"
                  class="form-control form-control-lg" v-model="email"
                />
              </div>
            </div>
            <label class="form-label" for="form3Examplev4" 
              ><h5>fullName</h5></label
            >
            <div class="mb-4 pb-2">
              <div class="form-outline">
                <input
                  type="text"
                  id="form3Examplev4"
                  class="form-control form-control-lg" v-model="fullName"
                />
              </div>
            </div>
            <h5>Status</h5>
            <div class="mb-4 pb-2">
              <select class="select" style="width: 100%" v-model="status">
                <option value="1">ACTIVATED</option>
                <option value="2">CREATED</option>
                <option value="3">BANNED</option>
              </select>
            </div>
            <button type="button" class="btn btn-primary" @click="updated">Save</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import { defineComponent, ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import useMixin from "../../mixins/common";
import store from "../../store/index";

export default defineComponent({
  setup() {
    const router = useRouter();
    const email = ref("");
    const fullName = ref("");
    const status = ref();
    const id = ref();
    const { post } = useMixin();
    const listuser =() => {
      router.push("/user");
    }
    const updated = async () => {
      const dataUpdate = {
        email: email.value,
        fullName: fullName.value,
        status: status.value,
      };
      const result = await post(`/admin/api/user/${id.value}`, dataUpdate);
      if (result.code === 200) {
        router.push("/user");
      }
    };
    onMounted(() => {
    id.value = router.currentRoute.value.params.id;
    })
    return {
      updated,
      listuser,
      email,
      id,
      fullName,
      status
    };
  },
});
</script>
