<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="8">
      <v-alert type="success" dismissible v-model="alertSuccess">บันทึกการอนุมัติเรียบร้อย</v-alert>
      <v-alert type="error" dismissible v-model="alertFailed">{{ alert_msg }}</v-alert>
      <v-dialog v-model="dialog" width="700">
        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>{{ dialog_details }}</v-card-title>
          <v-card-text>
            <v-select
              prepend-icon="mdi-bicycle"
              id="select_bike"
              :items="bikes"
              item-value="id"
              item-text="name"
              v-model="selectedBike"
              label="เลือกจักรยาน"
            ></v-select>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" id="btn_accept" text @click="approveBorrowedBike">อนุมัติ</v-btn>
            <v-btn color="error" text @click="dialog = false">ยกเลิก</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-card class="elevation-12">
        <v-toolbar color="amber darken-3" dark flat>
          <v-toolbar-title>
            <v-icon>mdi-bicycle</v-icon>&nbsp; คำร้องขอยืมรถจักรยาน
          </v-toolbar-title>
          <v-spacer />
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <v-btn @click="getAllBorrowedBikes" icon large v-on="on">
                <v-icon>mdi-refresh</v-icon>
              </v-btn>
            </template>
            <span>Source</span>
          </v-tooltip>
        </v-toolbar>
        <v-card-text>
          <v-alert type="info" v-model="alertInfo">{{ alert_msg }}</v-alert>
          <template v-for="(bb) in borrowedBikes">
            <v-card :key="bb.i" style="margin: 20px">
              <v-toolbar color="white darken-4" light>
                <v-icon>mdi-key</v-icon>
                {{ bb.id }}
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
            </v-card>
          </template>
        </v-card-text>
        <v-card-actions></v-card-actions>
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
      alert_msg: null,
      alertInfo: false,
      alertSuccess: false,
      alertFailed: false,
      selectedBorrowedBike: null,
      selectedStaff: null,
      dialog_details: null,
      dialog: false,
      selectedBike: null,
      bikes: [],
      borrowedBikes: []
    };
  },
  methods: {
    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
      this.alertInfo = false;
    },
    clearSelected() {
      this.selectedBorrowedBike = null;
      this.selectedBike = null;
    },
    approveBorrowedBike() {
      if (
        !this.selectedStaff ||
        !this.selectedBike ||
        !this.selectedBorrowedBike
      ) {
        this.clearAlert();
        this.dialog = false;
        this.alert_msg = "กรุณาเลือกข้อมูลให้ครบ!";
        this.alertFailed = true;
        return;
      }
      let body = {
        borrowedBikeId: this.selectedBorrowedBike,
        bikeId: this.selectedBike,
        staffId: this.selectedStaff
      };
      api
        .put("api/staff/borrowedbikerequest", JSON.stringify(body))
        .then(() => {
          this.dialog = false;
          this.clearAlert();
          this.clearSelected();
          this.alertSuccess = true;
          this.getAllBorrowedBikes();
        })
        .catch(e => {
          this.clearAlert();
          this.alert_msg = e.message;
          this.alertFailed = true;
        });
    },
    handleDialog(bb) {
      this.selectedBorrowedBike = bb.id;
      this.dialog_fullName = bb.roomBooking.student.fullName;
      this.dialog_details =
        bb.id +
        ":" +
        bb.roomBooking.student.fullName +
        ":" +
        bb.dateType.type +
        ":" +
        bb.bikeType.type;
      this.dialog = true;
      this.getBikes(bb.bikeType.id);
    },
    test() {
      alert("TEST()");
    },
    getBikes(btId) {
      this.bikes = [];
      let body = {
        bikeTypeId: btId
      };
      api
        .post("/api/bike2", body)
        .then(res => {
          this.bikes = res.data;
        })
        .catch(e => {
          this.dialog = false;
          this.alert_msg = e.message;
          this.alertFailed = true;
        });
    },
    getAllBorrowedBikes() {
      this.borrowedBikes = [];
      api
        .get("/api/borrowedbikes/bike_null")
        .then(res => {
          this.borrowedBikes = res.data;
          if (res.data.length == 0) {
            this.alertInfo = true;
            this.alert_msg = "ไม่มีคำร้อง.";
          } else {
            this.alertInfo = false;
          }
        })
        .catch(e => {
          this.clearAlert();
          this.alert_msg = e.message;
          this.alertFailed = true;
        });
    }
  },
  mounted() {
    this.getAllBorrowedBikes();
    var user = JSON.parse(localStorage.getItem("user"));
    this.selectedStaff = user.id;
  }
};
</script>
