<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
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
            v-model="selectedBranches"
            :items="Branches"
            label="เลือกสำนักวิชา"
            item-text="branches"
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
    this.getAllBranches();
  },
  data() {
    return {
      selectedStudents: null,
      selectedRooms: null,
      selectedBranches: null,
      Students: [],
      Rooms: [],
      Branches: []
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
    getAllBranches() {
      api
        .get("/api/branches")
        .then(res => {
          this.Branches = res.data;
          console.log("loading branches");
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
          console.log("ห้อง " + this.selectedRooms)
          console.log(JSON.parse(JSON.stringify(res.data)))
          let knh = res.data;
          console.log("มีคนในห้องทั้งหมด = " + knh.length)
          if (knh.length >= 3  ){
            alert ("Room "+this.selectedRooms+" is Full")
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
        !this.selectedBranches
      ) {
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
      } else {
        this.registered();
      }
    },
    registered() {
      api
        .post(
          "/api/roombooking" +
            "/" +
            this.selectedStudents +
            "/" +
            this.selectedRooms +
            "/" +
            this.selectedBranches +
            "/"
        )
        .then(res => {
          alert("จองห้องพักสำเร็จ!");
          console.log(JSON.parse(JSON.stringify(res.data)));
          this.$router.go("/");
        })
        .catch(e => {
          console.log(e);
        });
    },
    Back() {}
  }
};
</script>
