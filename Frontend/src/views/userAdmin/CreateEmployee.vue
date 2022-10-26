<template>
  <section class="h-100 h-custom gradient-custom-2">
    <div class="container py-5 h-100" >
      <button type="button" class="btn btn-primary" @click="listuser">Come Back</button>
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div
          class="card card-registration card-registration-2"
          style="border-radius: 15px"
        >
          <div class="p-5">
            <h3 class="fw-normal mb-5" style="color: #4835d4">
              From Create Staff
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
            <h5>Department</h5>
            <div class="mb-4 pb-2">
              <select class="select" style="width: 100%" v-model="departmentId">
                <option value="1">TECHNICAL DEPARTMENT</option>
                <option value="2">ACCOUNTING DEPARTMENT</option>
                <option value="3">HUMAN RESOUCES DEPARTMENT</option>
                <option value="4">EXECUTIVE BOARD</option>
              </select>
            </div>
            <h5>Position</h5>
            <div class="mb-4 pb-2">
              <select class="select" style="width: 100%" v-model="positionId">
                <option value="1">GENERAL MANAGER</option>
                <option value="2">MANAGER</option>
                <option value="3">ACCOUNTANT</option>
                <option value="4">HR</option>
                <option value="5">OFFICIAL STAFF</option>
              </select>
            </div>
            <h5>Role</h5>
            <div class="mb-4 pb-2">
              <select class="select" style="width: 100%" v-model="role">
                <option value="1">ADMIN</option>
                <option value="2">STAFF</option>
                <option value="3">EDITER</option>
              </select>
            </div>
            <button type="button" class="btn btn-primary" @click="created">Save</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import useMixin from "../../mixins/common";
import store from "../../store/index";

export default defineComponent({
  setup() {
    const router = useRouter();
    const email = ref("");
    const fullName = ref("");
    const departmentId = ref();
    const positionId = ref();
    const role = ref();
    const { post } = useMixin();
    const listuser =() => {
      router.push("/user");
    }
    const created = async () => {
      const dataCreate = {
        email: email.value,
        fullName: fullName.value,
        departmentId: departmentId.value,
        positionId: positionId.value,
        role: role.value,
      };
      const result = await post("/admin/api/create-employee", dataCreate);
      if (result.code === 200) {
        router.push("/user");
      }
    };
    return {
      created,
      listuser,
      email,
      fullName,
      departmentId,
      positionId,
      role
    };
  },
});
</script>
