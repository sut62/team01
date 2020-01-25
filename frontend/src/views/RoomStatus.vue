<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <v-alert type="success" dismissible v-model="alertSuccess">พบข้อมูล</v-alert>
        <v-alert type="error" dismissible v-model="alertFailed">ไม่พบข้อมูล!</v-alert>
        <h1 class="display-2 font-weight-bold mb-3">สถานะห้องพัก</h1>

         <v-row justify="center" class="pb-0 mb-0">
            <v-col cols="3">
            <v-text-field solo 
            label="กรอกเลขห้อง"
            v-model="roomName"
            @keyup.enter="SearchRoomname"/>
            </v-col>
            <v-col cols="1">
                <v-btn @click="SearchRoomname" large class="light-blue darken-1">Search</v-btn>
            </v-col>
        </v-row>

        <v-card v-if="Status">
            <v-data-table
              :headers="headers"
              :items="roomBooking"
              :items-per-page="5"
              class="elevation-3"
            ></v-data-table>
        </v-card> 
        <v-card v-if="!Status">
            <v-data-table
              :headers="headers"
              :items="SearchRoom"
              :items-per-page="5"
              class="elevation-3"
            ></v-data-table>
        </v-card> 

    </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api.js";
export default {
  mounted() {
    this.getAllRoomBooking();
  },
  data() {
    return {
      roomBooking: [],
      SearchRoom: [],
      roomName: undefined,
      Status: true,
      headers: [
        { text: "ห้อง", value: "rooms.roomId" },
        { text: "รหัสนักศึกษา", value: "student.studentId" },
        { text: "ชื่อนักศึกษา", value: "student.fullName" },
        { text: "สาขาวิชา", value: "student.bachelor" }
      ],
      alertFailed: false,
      alertSuccess: false,
    };
  },

  methods: {
     getAllRoomBooking() {
      api
        .get("/api/GetAndSortData")
        .then(res => {
          this.roomBooking = res.data;
          console.log("loading roombooking");
          console.log(JSON.parse(JSON.stringify(res.data)));
          // this.clearAlert();
        })
        .catch(e => {
          console.log(e);
        });
    },
    SearchRoomname() {
      api
        .get("/api/roombooking/"+this.roomName)
        .then(res => {
          this.SearchRoom = res.data;
          console.log("loading roombooking");
          console.log(JSON.parse(JSON.stringify(res.data)));
          this.clearAlert();
          if (this.SearchRoom.length == 0) {
            this.alertFailed = true;
          } else {
            this.alertSuccess = true;
            this.Status = false;
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    clearAlert() {
    this.alertSuccess = false;
    this.alertFailed = false;
  }

  },
  
};
</script>