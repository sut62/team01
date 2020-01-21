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
          <template v-for="(bb) in borrowedBikes">
            <v-card :key="bb.i" style="margin: 20px">
              <v-toolbar color="white darken-4" light>
                <v-icon>mdi-key</v-icon>
                {{ bb.id }}
                <!-- <v-btn color="blue" text></v-btn> -->
                <v-icon>mdi-account</v-icon>
                {{ bb.roomBooking.student.fullName }}
                |
                <v-icon>mdi-calendar-today</v-icon>
                {{ bb.dateType.type }} |
                <v-icon>mdi-bicycle</v-icon>
                {{ bb.bikeType.type }}
                <v-spacer></v-spacer>
                <v-btn color="amber darken-3" dark @click="handleDialog(bb)">เลือกคำร้อง</v-btn>
              </v-toolbar>
              <v-divider></v-divider>
              <v-dialog v-model="dialog" width="500">
                <v-card>
                  <v-card-title class="headline grey lighten-2" primary-title>WTF</v-card-title>
                  <v-card-text>{{ dialog_fullName }}</v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="success" text @click="handleApproveBorrowedBike">อนุมัติ</v-btn>
                    <v-btn color="error" text @click="dialog = false">ยกเลิก</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
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
      dialog_fullName: null,
      dialog: false,
      selectedBike: null,
      bikes: [],
      search: null,
      borrowedBikes: []
    };
  },
  methods: {
    handleApproveBorrowedBike() {},
    handleDialog(bb) {
      this.dialog = true;
      this.dialog_fullName = bb.roomBooking.student.fullName;
      console.log(JSON.parse(JSON.stringify(bb)));
    },
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
          console.log(JSON.parse(JSON.stringify(res.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllBorrowedBikes() {
      api
        .get("/api/borrowedbikes")
        .then(res => {
          this.borrowedBikes = res.data;
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
