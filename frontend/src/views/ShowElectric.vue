<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="4">
          <v-alert type="success" dismissible v-model="alertSuccess">ค้นหาห้องสำเร็จ</v-alert>
          <v-alert type="error" dismissible v-model="alertFailed">ไม่พอห้อง!</v-alert>
          <v-card class="elevation-12">
            <v-toolbar color="amber" light flat>
              <v-icon>mdi-paper-roll</v-icon>&nbsp;&nbsp;
              <v-toolbar-title>แสดงข้อมูลเครื่องไฟฟ้า</v-toolbar-title>
              <div class="flex-grow-1"></div>
            </v-toolbar>
            <v-card-text>
              <v-row align="center" sm="6">
                <v-col cols="6">
                  <v-text-field
                    v-model="roomNumber"
                    @keyup.enter="getSearch"
                    label="กรอกหมายเลขห้อง"
                  ></v-text-field>
                </v-col>
                <v-col cols="3">
                  <v-btn class="yellow lighten-3" @click="getSearch">ค้นหา</v-btn>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>



<script>
import api from "../Api.js";
export default {
  mounted() {
    this.getAllStaffs();
    this.getAllElectrictype();
  },
  data() {
    return {
      staff: [],
      selectedStaff: null,
      details: null,
      StdNames: [],
      roomNumber: undefined,
      alertSuccess: false,
      alertFailed: false,

      roomBooking: [],
      selectedRoomBooking: null,

      electricType: [],
      selectedElectrictype: null
    };
  },
  methods: {
    getAllStaffs() {
      this.staff = JSON.parse(localStorage.getItem("user"));
    },

    getAllElectrictype() {
      api
        .get("/api/electrictypes/")
        .then(response => {
          this.electricType = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    getAllRoomBooking() {
      api
        .get("/api/roombooking/")
        .then(response => {
          this.roomBooking = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
    },

    getSearch() {
      api
        .get("/api/roombooking/" + this.roomNumber)
        .then(response => {
          this.StdNames = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
          this.clearAlert();
          this.alertSuccess = true;
        })
        .catch(e => {
          console.log("Error in getSearch() :" + e);
          this.clearAlert();
          this.alertFailed = true;
        });
    }
  }
};
</script>
