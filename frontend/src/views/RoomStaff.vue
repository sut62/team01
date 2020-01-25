<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลการจองห้องพักของนักศึกษา</h1>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="roomBooking" :items-per-page="5" class="elevation-3"></v-data-table>
      </v-col>
    </v-row>


  </v-container>
</template>

<script>
import api from "../Api.js";
export default {
   mounted() {
    // this.getAlStudents();
    //  this.getAllRooms();
    // this.getAllBed();
    this.getAllRoomBooking();
  },
  data() {
    return {
      roomBooking: [],
      // Students: [],
      // Rooms: [],
      // Bed: [],
      headers: [
        
        { text: "ห้อง", value: "rooms.roomId" },
        { text: "รหัสนักศึกษา", value: "student.studentId" },
        { text: "ชื่อนักศึกษา", value: "student.fullName" },
        { text: "สาขาวิชา", value: "student.bachelor" },
        { text: "หมายเหตุ", value: "detail" },
      ],
    };
  },

  methods: {
    // back() {
    //   this.$router.push("/home");
    // },
    //  getAllRooms() {
    //   api
    //     .get("/api/GetAndSortData")
    //     .then(res => {
    //       this.Rooms = res.data;
    //       console.log("loading rooms");
    //       console.log(JSON.parse(JSON.stringify(res.data)));
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },
      getAllRoomBooking() {
      api
        .get("/api/GetAndSortData")
        .then(res => {
          this.roomBooking = res.data;
          console.log("loading roombooking");
          console.log(JSON.parse(JSON.stringify(res.data)));
        })
        .catch(e => {
          console.log(e);
        });
    }
  },

  // mounted() {
  //   this.getAllRepair();
  // }
};
</script>