<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="8">
      <v-card class="elevation-12">
        <v-toolbar color="amber darken-3" dark flat>
          <v-toolbar-title>
            <v-icon>mdi-bicycle</v-icon>&nbsp; คำร้องขอยืมรถจักรยาน
          </v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <template v-for="bb in borrowedBikes">
            <v-card :key="bb.id">
              <v-toolbar
                color="amber darken-3"
                dark
              >{{ bb.id }} | {{ bb.roomBooking.student.fullName }} | {{ bb.dateType.type }} | {{ bb.bikeType.type }}</v-toolbar>
              <v-card-title primary-title></v-card-title>
              <v-card-text>
                <v-select
                  v-model="selectedBike"
                  :items="getBikes(bb.bikeType.id)"
                  item-text="name"
                  label="เลือกจักรยาน"
                ></v-select>
                {{ selectedBike }} asd
              </v-card-text>
              <v-card-actions>
                <v-btn color="success" @click="test">อนุมัติ</v-btn>
              </v-card-actions>
            </v-card>
          </template>
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
  name: "approveBorrowedBike",
  data() {
    return {
      selectedBike: null,
      bikes: [],
      search: null,
      borrowedBikes: [],
      headers: [
        {
          text: "BorrowedBikeId",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "Student name", value: "roomBooking.student.fullName" },
        { text: "Bike type", value: "bikeType.type" }
      ]
    };
  },
  methods: {
    test() {
      return [{ name: "1234" }, { name: "456" }];
    },
    getBikes(btId) {
      let body = {
        bikeTypeId: btId
      };
      console.log("bikeTypdId = " + body.bikeTypeId);
      // let bikes = [];
      api
        .post("/api/bike", body)
        .then(res => {
          this.bikes = res.data;
          // bikes = res.data;
          console.log(JSON.parse(JSON.stringify(res.data)));
        })
        .catch(e => {
          console.log(e);
          return null;
        });
    },
    getAllBorrowedBikes() {
      api
        .get("/api/borrowedbikes")
        .then(res => {
          this.borrowedBikes = res.data;
          console.log("borrowedBikes: ");
          console.log(JSON.parse(JSON.stringify(this.borrowedBikes)));
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getAllBorrowedBikes();
  }
};
</script>
