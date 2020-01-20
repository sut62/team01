<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <v-alert type="success" dismissible v-model="alertSuccess"
        >บันทึกข้อมูลสำเร็จ</v-alert
      >
      <v-alert type="error" dismissible v-model="alertFailed"
        >กรุณาเลือกข้อมูลให้ครบทุกช่อง!</v-alert
      >
      <v-card class="elevation-12">
        <v-toolbar color="primary" dark flat>
          <v-icon large>mdi-home</v-icon>
          <v-toolbar-title>จองห้องพัก</v-toolbar-title>
        </v-toolbar>

        <v-card-text>
          <v-select
            v-model="selectedStudents"
            :items="Students"
            label="นักศึกษา"
            item-text="fullName"
            item-value="id"
          ></v-select>

          <v-select
            v-model="selectedRooms"
            :items="Rooms"
            label="เลือกห้อง"
            item-text="roomId"
            item-value="id"
          ></v-select>

          <v-select
            v-model="selectedBed"
            :items="Bed"
            label="เลือกเตียง"
            item-text="bed"
            item-value="id"
          ></v-select>
        </v-card-text>

        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="success" @click="Save">จองห้องพัก</v-btn>
          <v-btn color="secondary" router-link to="/">ยกเลิก</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api.js";

export default {
  watch: {
    selectedRooms: function() {
      this.checkStudentInRoom();
    }
  },
  mounted() {
    this.getAlStudents();
    this.getAllRooms();
    this.getAllBed();
  },
  data() {
    return {
      selectedStudents: null,
      selectedRooms: null,
      selectedBed: null,
      Students: [],
      Rooms: [],
      Bed: [],
      alertSuccess: false,
      alertFailed: false
    };
  },
  methods: {
    getAlStudents() {
      this.Students = JSON.parse(localStorage.getItem("user"));
    },
    getAllRooms() {
      api
        .get("/api/rooms")
        .then(res => {
          this.Rooms = res.data;
          console.log("loading rooms");
          console.log(JSON.parse(JSON.stringify(res.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllBed() {
      api
        .get("/api/bed")
        .then(res => {
          this.Bed = res.data;
          console.log("loading bed");
          console.log(JSON.parse(JSON.stringify(res.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    checkStudentInRoom() {
      // this.selectedRooms = 33
      api
        .get("/api/roombooking/fronk/" + this.selectedRooms)
        .then(res => {
          console.log("ห้อง " + this.selectedRooms);
          console.log(JSON.parse(JSON.stringify(res.data)));
          let knh = res.data;
          console.log("มีคนในห้องทั้งหมด = " + knh.length);
          if (knh.length >= 3) {
            alert("Room " + this.selectedRooms + " is Full");
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    Save() {
      if (
        !this.selectedStudents ||
        !this.selectedRooms ||
        !this.selectedBed
      ) {
        this.clearAlert();
        this.alertFailed = true;
        // alert("กรุณาเลือกข้อมูลให้ครบ!");
      } else {
        this.registered();
      }
    },
    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
    },
    registered() {
      // เพิ่มเข้ามาใหม่
      let newelepayload = {
        student_id: this.selectedStudents,
        room_id: this.selectedRooms,
        bed_id: this.selectedBed
      };
      console.log(newelepayload);
      api
        .post("/api/roombooking/new", newelepayload)
        .then(() => {
          this.clearAlert();
          this.alertSuccess = true;
          //alert("บันทึกข้อมูลสำเร็จ!");
          this.selectedStudents = null;
          this.selectedRooms = null;
          this.selectedBed = null;
        })
        .catch(e => {
          console.log(e);
          this.clearAlert();
          this.alertFailed = true;
        });
    },
    Back() {}
  }
};
</script>
