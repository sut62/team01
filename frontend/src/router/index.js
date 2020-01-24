import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: Home
  },
  {
    path: "/",
    name: "publicContent",
    component: () => import("../views/Public.vue")
  },
  {
    path: "/borrowedbike",
    name: "borrowedBike",
    component: () => import("../views/BorrowedBike.vue")
  },
  {
    path: "/signin",
    name: "sigin",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Signin.vue")
  },
  {
    path: "/profile",
    name: "profile",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Profile.vue")
  },
  {
    path: "/staff/signin",
    name: "staffsignin",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/StaffSignin.vue")
  },
  {
    path: "/staff/enrollvehicle",
    name: "enrollvehicle",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/EnrollVehicle.vue")
  },
  {
    path: "/staff/approve/borrowedbike",
    name: "approveborrowedbike",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ApproveBorrowedBike.vue")
  },
  {
    path: "/staff/electricregister",
    name: "electricregister",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ElectricRegister.vue")
  },
  {
    path: "/staff/repairdetail",
    name: "repairdetail",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/RepairDetail.vue")
  },
  {
    path: "/roombooking",
    name: "roombooking",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/RoomBooking.vue")
  },
  {
    path: "/repair",
    name: "repair",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Repair.vue")
  },
  {
    path: "/staff/packageManagement",
    name: "packageManagement",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/PackageManagement.vue")
  },
  {
    path: "/staff/showElectric",
    name: "showElectric",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ShowElectric.vue")
  },
  {
    path: "/staff/confirmPackage",
    name: "confirmPackage",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ConfirmPackage.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});
router.beforeEach((to, from, next) => {
  const publicPages = ["/signin", "/", "/register", "/staff/signin"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");

  // try to access a restricted page + not logged in
  if (authRequired && !loggedIn) {
    return next("/signin");
  }

  next();
});
export default router;
