<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <v-card class="elevation-12">
        <v-toolbar color="primary" dark flat>
          <v-toolbar-title>เข้าสู่ระบบในฐานะนักศึกษา</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field
              label="Login"
              name="login"
              prepend-icon="mdi-account"
              type="text"
              v-model="username"
            />

            <v-text-field
              id="password"
              label="Password"
              name="password"
              prepend-icon="mdi-lock"
              type="password"
              v-model="password"
            />
          </v-form>
        </v-card-text>
        <v-card-actions>
          <a @click="handleStaffSignin">เข้าสู่ระบบสำหรับที่ปรึกษาหอพัก?</a>
          <v-spacer />
          <v-btn color="primary" @click="signin">Login</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api.js";

export default {
  name: "sigin",
  data() {
    return {
      username: undefined,
      password: undefined
    };
  },
  methods: {
    handleStaffSignin() {
      this.$router.push("/staff/signin");
    },
    signin() {
      let user = {
        username: this.username,
        password: this.password
      };
      api
        .post("/auth/login", JSON.stringify(user))
        .then(res => {
          let user = res.data;
          alert("Signin successfully..." + user.username);
          localStorage.setItem("user", JSON.stringify(user));
          this.$router.go("/home");
        })
        .catch(e => {
          alert(e);
        });
    }
  },
  mounted() {
    if (localStorage.getItem("user")) {
      this.$router.push("/home");
    }
  }
};
</script>

<style></style>
