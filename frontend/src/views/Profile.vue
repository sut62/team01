<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="8">
      <template>
        <v-card class="mx-auto" max-width="600">
          <v-card-title primary-title>
            Profile
          </v-card-title>
          <v-card-text>
            <v-list v-for="(item, i) in user" :key="i">
              <li>{{ i }} : {{ item }}</li>
            </v-list>
          </v-card-text>
        </v-card>
      </template>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      user: {}
    };
  },
  methods: {},
  mounted() {
    let user = JSON.parse(localStorage.getItem("user"));
    this.user = {
      id: user.id,
      name: user.fullName,
      studentId: user.studentId
    };
  }
};
</script>
