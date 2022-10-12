import { createRouter, createWebHistory } from "vue-router";


const routes = [
  {
    path: "/",
    component: () => import('../components/TheContainer.vue'),
    children: [
      {
        path: 'user',
        component: () => import('../views/userAdmin/UserAdmin.vue'),
      },
      {
        path: 'staff',
        component: () => import('../views/staff/List.vue'),
      },
    ],
  },
  {
    path: '/login',
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
router.beforeEach(async (to, from) => {
  console.log(to);
  console.log(from);
  return true;
});

export default router;
