<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="8">
      <v-card class="elevation-12">
        <v-toolbar color="primary" dark flat>
          <v-toolbar-title>จักรยานที่ฉันแจ้งยืม</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-data-table
            :headers="headers"
            :items="borrowedBikes"
            :items-per-page="5"
            class="elevation-1"
          ></v-data-table>
        </v-card-text>
        <v-card-actions>
          <!-- <v-btn color="success" @click="requestData">test</v-btn> -->
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api";

export default {
  name: "myBorrowedBike",
  data() {
    return {
      borrowedBikes: [],
      headers: [
        {
          text: "BorrowedBikeID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "BikeType", value: "bikeType.type" },
        { text: "BorrowedDate", value: "borrowedDate" }
      ]
    };
  },
  methods: {
    getMyBorrowedBikes() {
      let user = JSON.parse(localStorage.getItem("user"));
      api
        .post(
          "/api/myborrowedbike",
          JSON.stringify({
            username: user.username,
            password: user.password
          })
        )
        .then(res => {
          this.borrowedBikes = res.data;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getMyBorrowedBikes();
  }
};
</script>
