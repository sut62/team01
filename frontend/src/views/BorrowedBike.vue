<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <!-- TODO: เพิ่มการแจ้งเตือนเพื่อให้ katalon ตรวจจับได้ -->
      <v-alert type="success" dismissible v-model="alertSuccess">
        บันทึกข้อมูลการแจ้งยืมเรียบร้อย
      </v-alert>
      <v-alert type="error" dismissible v-model="alertFailed">
        กรุณาเลือกข้อมูลให้ครบทุกช่อง!
      </v-alert>
      <v-card class="elevation-12">
        <v-toolbar color="primary" dark flat>
          <v-toolbar-title>ยืมจักรยาน</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-select
              v-model="selectedRoomBooking"
              :items="roomBookings"
              item-text="student.fullName"
              item-value="id"
              label="เลือกนักศึกษา"
              outlined
            ></v-select>
            <v-select
              v-model="selectedBikeTypes"
              :items="bikeTypes"
              item-text="type"
              item-value="id"
              label="เลือกประเภทจักรยาน"
              outlined
            ></v-select>
            <v-select
              v-model="selectedDateType"
              :items="dateTypes"
              item-text="type"
              item-value="id"
              label="เลือกประเภทวันยืม"
              outlined
            ></v-select>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn color="primary" @click="handleBorrowBike">ยืนยัน</v-btn>
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
      let body = {
        bikeType_id: this.selectedBikeTypes,
        roomBooking_id: this.selectedRoomBooking,
        dateType_id: this.selectedDateType
      };

      api
        .post("/api/borrowedbike", body)
        .then(() => {
          // Katalon Recorder จับไม่เจอ ต้องใช้ Katalon Studio
          // alert("บันทึกข้อมูลการแจ้งยืมเรียบร้อย.");
          // this.$router.go();
          //---------------------------------------------

          // ปิด Alert อันเก่าไปก่อน เดี๋ยวอันใหม่เด้งมาทับ
          this.clearAlert();
          // แจ้งเตือนว่าบันทึกเสร็จ
          this.alertSuccess = true;
          this.clearCombobox();
        })
        .catch(() => {
          this.clearAlert();
          this.alertFailed = true;
        });
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
    // getStudentDetail() {
    //   this.students.push(JSON.parse(localStorage.getItem("user")));
    // },
    getSpecificRoomBookings() {
      let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        student_id: user.id
      };
      console.log(body);
      api
        .post("/api/roombooking/student", JSON.stringify(body))
        .then(res => {
          this.roomBookings = res.data;
          console.log("getRoomBookingWhereStudent");
          console.log(res.data);
        })
        .catch(e => {
          console.log(e);
        });
      //TODO: ถ้า student จองห้องแล้ว ให้เอาเมนูจองห้องออก หรือ เข้าจองห้องไม่ได้
    },
    getAllBikeTypes() {
      api
        .get("/api/biketypes")
        .then(res => {
          this.bikeTypes = res.data;
          // console.log(res.data)
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
          // console.log(res.data)
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    // this.getStudentDetail();
    this.getAllBikeTypes();
    this.getAllDateTypes();
    this.getSpecificRoomBookings();
  }
};
</script>

<style></style>
