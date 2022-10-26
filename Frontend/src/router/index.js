import { createRouter, createWebHistory } from "vue-router";
import store from "../store/index";
import api from "../boot/axios";
const writeRule =['ADMIN', 'STAFF', 'EDITER'];
const routes = [
  {
    path: "/",
    component: () => import('../components/TheContainer.vue'),
    children: [
      {
        path: 'profile',
        component :() => import('../views/user/Profile.vue'),
      },
      {
        path: 'user',
        component: () => import('../views/userAdmin/UserAdmin.vue'),

      },
      {
        path: 'editprofile',
        component: () => import('../views/user/EditProfile.vue'),
      },
      {
        path: 'user/created',
        component: () => import('../views/userAdmin/CreateEmployee.vue'),
      },
      {
        path: 'user/updated/:id',
        component: () => import('../views/userAdmin/UpdateEmployee.vue'),
      },
    ],
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/authAdmin/Login.vue'),
  },

  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
router.beforeEach(async (to, from, next) => {
  const token = store.getters['common/getTOKEN'];
  if (token) {
    next();
  }
  try {
    const { data } = await api("/auth/api/check_login");
    if (data) {
      // Không có dữ liệu đẩy về login
      store.dispatch('common/updateAdminUser', data.payload);
      next();
    } else {
      next("login");
    }

  }
  catch (err) {
    next("login");
  }

});


export default router;
