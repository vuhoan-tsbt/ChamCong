<template>
  <div class="">
    <table class="table">
      <thead>
        <tr>
          <td>id</td>
          <td>fullName</td>
          <td>email</td>
          <td>StaffCode</td>
          <td>date Of Bith</td>
          <td>address</td>
          <td>create At</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in listUser" :key="item"> 
            <td>{{item.id}}</td>
            <td>{{item.fullName}}</td>
            <td>{{item.email}}</td>
            <td>{{item.staffCode}}</td>
            <td>{{item.dateOfBirth}}</td>
            <td>{{item.address}}</td>
            <td>{{item.createdAt}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import { defineComponent, onMounted, ref } from "vue";
import useMixin from '../../mixins/common';

export default defineComponent({
  setup() {
    const { get } = useMixin();
    const listUser = ref();
    onMounted(async () => {
      const result = await get('user/api/list-user');
      listUser.value = result.payload.data;
    });
    return {
        listUser,
    }
  },
});
</script>
