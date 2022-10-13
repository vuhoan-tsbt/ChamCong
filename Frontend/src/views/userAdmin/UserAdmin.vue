<template>
  <div class="table">
    <button type="button" class="btn btn-primary" @click="createemployee">Create Staff</button>
    <table class="table table-dark table-striped">
      <thead>
        <tr>
          <td scope="col">id</td>
          <td scope="col">fullName</td>
          <td scope="col">email</td>
          <td scope="col">StaffCode</td>
          <td scope="col">date Of Bith</td>
          <td scope="col">address</td>
          <td scope="col">create At</td>
          <td scope="col">Department</td>
          <td scope="col">Position</td>
          <td scope="col"></td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in listUser" :key="item">
          <td>{{ item.id }}</td>
          <td>{{ item.fullName }}</td>
          <td>{{ item.email }}</td>
          <td>{{ item.staffCode }}</td>
          <td>{{ item.dateOfBirth }}</td>
          <td>{{ item.address }}</td>
          <td>{{ item.createdAt }}</td>
          <td>{{ item.department }}</td>
          <td>{{ item.position }}</td>
          <td>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-brush"
              viewBox="0 0 16 16"
              @click="edit(item.id)"
            >
              <path
                d="M15.825.12a.5.5 0 0 1 .132.584c-1.53 3.43-4.743 8.17-7.095 10.64a6.067 6.067 0 0 1-2.373 1.534c-.018.227-.06.538-.16.868-.201.659-.667 1.479-1.708 1.74a8.118 8.118 0 0 1-3.078.132 3.659 3.659 0 0 1-.562-.135 1.382 1.382 0 0 1-.466-.247.714.714 0 0 1-.204-.288.622.622 0 0 1 .004-.443c.095-.245.316-.38.461-.452.394-.197.625-.453.867-.826.095-.144.184-.297.287-.472l.117-.198c.151-.255.326-.54.546-.848.528-.739 1.201-.925 1.746-.896.126.007.243.025.348.048.062-.172.142-.38.238-.608.261-.619.658-1.419 1.187-2.069 2.176-2.67 6.18-6.206 9.117-8.104a.5.5 0 0 1 .596.04zM4.705 11.912a1.23 1.23 0 0 0-.419-.1c-.246-.013-.573.05-.879.479-.197.275-.355.532-.5.777l-.105.177c-.106.181-.213.362-.32.528a3.39 3.39 0 0 1-.76.861c.69.112 1.736.111 2.657-.12.559-.139.843-.569.993-1.06a3.122 3.122 0 0 0 .126-.75l-.793-.792zm1.44.026c.12-.04.277-.1.458-.183a5.068 5.068 0 0 0 1.535-1.1c1.9-1.996 4.412-5.57 6.052-8.631-2.59 1.927-5.566 4.66-7.302 6.792-.442.543-.795 1.243-1.042 1.826-.121.288-.214.54-.275.72v.001l.575.575zm-4.973 3.04.007-.005a.031.031 0 0 1-.007.004zm3.582-3.043.002.001h-.002z"
              />
            </svg>
            ----
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-x-octagon"
              viewBox="0 0 16 16"
              @click="deleted(item.id)"
            >
              <path
                d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353L4.54.146zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1H5.1z"
              />
              <path
                d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"
              />
            </svg>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import { defineComponent, onMounted, ref } from "vue";
import useMixin from "../../mixins/common";
import { useRouter } from 'vue-router';

export default defineComponent({
  setup() {
    const { get,del } = useMixin();
    const listUser = ref();
    const router = useRouter();
    const createemployee =() => {
      router.push("/user/created");
    }
    const edit = (id) => {
      router.push(`/user/updated/${id}`);
    };
    const deleted =async (id) => {
      const result = await del(`user/api/user/${id}`);
      if(result.code == 200){
        await search();

      }
    };
    
    const search = async () => {
      const result = await get("user/api/list-user");
      listUser.value = result.payload.data;
    }

    onMounted(async () => {
      await search();
    });
    return {
      listUser,
      edit,
      deleted,
      createemployee,
      search
    };
  },
});
</script>
