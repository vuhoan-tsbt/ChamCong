<template>
    <div class="c-app">
      <div class="row" style="display: flex;">
        <SideBar />
        <div style="width:calc(100% - 290px)">
          <Header />
          <router-view v-slot="{ Component }">
            <component :is="Component" />
          </router-view>
        </div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from "vue";
import Header from "./Headers.vue";
import SideBar from "./SideBar.vue";

export default defineComponent({
  components: {
    Header,
    SideBar,
  },
  setup() {
    return {};
  },
});
</script>
<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
