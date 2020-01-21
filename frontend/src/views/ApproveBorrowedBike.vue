<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="8">
      <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลการแจ้งยืมเรียบร้อย</v-alert>
      <v-alert type="error" dismissible v-model="alertFailed">กรุณาเลือกข้อมูลให้ครบทุกช่อง!</v-alert>
      <v-dialog v-model="dialog" width="500">
        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>{{ dialog_details }}</v-card-title>
          <v-card-text>
            <v-select
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
            <v-btn color="success" text @click="handleApproveBorrowedBike">อนุมัติ</v-btn>
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
      alertSuccess: false,
      alertFailed: false,
      selectedBorrowedBike: null,
      selectedStaff: null,
      dialog_details: null,
      dialog_fullName: null,
      dialog: false,
      selectedBike: null,
      bikes: [],
      search: null,
      borrowedBikes: []
    };
  },
  methods: {
    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
    },
    clearSelected() {
      this.selectedBorrowedBike = null;
      this.selectedBike = null;
    },
    handleApproveBorrowedBike() {
      let body = {
        borrowedBikeId: this.selectedBorrowedBike,
        bikeId: this.selectedBike,
        staffId: this.selectedStaff
      };
      console.log("Body = ");
      console.log(body);
      api
        .put("api/staff/borrowedbikerequest", JSON.stringify(body))
        .then(res => {
          console.log(res.data);
          this.dialog = false;
          this.clearAlert();
          this.clearSelected();
          this.alertSuccess = true;
        })
        .catch(e => {
          console.log(e);
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
      console.log(JSON.parse(JSON.stringify(bb)));
    },
    test() {
      alert("TEST()");
    },
    getBikes(btId) {
      // clear old bikes
      this.bikes = [];
      let body = {
        bikeTypeId: btId
      };
      console.log("bikeTypdId = " + body.bikeTypeId);
      // let bikes = [];
      api
        .post("/api/bike", body)
        .then(res => {
          var bike = res.data;
          var i;
          for (i = 0; i < bike.length; i++) {
            if (bike[i].available == true) {
              this.bikes.push(bike[i]);
            }
          }
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
    var user = JSON.parse(localStorage.getItem("user"));
    this.selectedStaff = user.id;
    console.log(this.selectedStaff);
  }
};
</script>
