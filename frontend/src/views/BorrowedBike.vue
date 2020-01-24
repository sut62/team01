<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลการแจ้งยืมเรียบร้อย</v-alert>
      <v-alert type="error" dismissible v-model="alertFailed">กรุณาเลือกข้อมูลให้ครบทุกช่อง!</v-alert>
      <v-card class="elevation-12">
        <v-toolbar color="primary" dark flat>
          <v-toolbar-title>
            <v-icon>mdi-bicycle</v-icon>&nbsp; ยืมจักรยาน
          </v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-select
              prepend-icon="mdi-account"
              id="select_student"
              v-model="selectedRoomBooking"
              :items="roomBookings"
              item-text="student.fullName"
              item-value="id"
              label="เลือกนักศึกษาที่จองห้องแล้ว"
              outlined
            ></v-select>
            <v-select
              prepend-icon="mdi-bicycle"
              v-model="selectedBikeTypes"
              :items="bikeTypes"
              item-text="type"
              item-value="id"
              label="เลือกประเภทจักรยาน"
              outlined
            ></v-select>
            <v-select
              prepend-icon="mdi-calendar"
              v-model="selectedDateType"
              :items="dateTypes"
              item-text="type"
              item-value="id"
              label="เลือกประเภทระยะเวลา"
              outlined
            ></v-select>
          </v-form>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer />
          <v-btn block large color="primary" @click="handleBorrowBike">ยืนยัน</v-btn>
          <v-spacer />
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api.js";

export default {
  name: "borrowedBike",
  data() {
    return {
      student_id: null,
      roomBookings: [],
      bikeTypes: [],
      dateTypes: [],
      selectedRoomBooking: undefined,
      selectedBikeTypes: undefined,
      selectedDateType: undefined,
      alertSuccess: false,
      alertFailed: false
    };
  },
  methods: {
    handleBorrowBike() {
      if (
        !this.selectedRoomBooking ||
        !this.selectedBikeTypes ||
        !this.selectedDateType
      ) {
        this.clearAlert();
        this.alertFailed = true;
      } else {
        let body = {
          bikeType_id: this.selectedBikeTypes,
          roomBooking_id: this.selectedRoomBooking,
          dateType_id: this.selectedDateType
        };

        api
          .post("/api/borrowedbike", body)
          .then(() => {
            this.clearAlert();
            this.alertSuccess = true;
            this.clearCombobox();
          })
          .catch(() => {
            this.clearAlert();
            this.alertFailed = true;
          });
      }
    },
    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
    },
    clearCombobox() {
      this.selectedRoomBooking = null;
      this.selectedBikeTypes = null;
      this.selectedDateType = null;
    },
    getSpecificRoomBookings() {
      let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        student_id: user.id
      };
      api
        .post("/api/roombooking/student", JSON.stringify(body))
        .then(res => {
          this.roomBookings = res.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllBikeTypes() {
      api
        .get("/api/biketypes")
        .then(res => {
          this.bikeTypes = res.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllDateTypes() {
      api
        .get("/api/datetypes")
        .then(res => {
          this.dateTypes = res.data;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getAllBikeTypes();
    this.getAllDateTypes();
    this.getSpecificRoomBookings();
  }
};
</script>

<style></style>
