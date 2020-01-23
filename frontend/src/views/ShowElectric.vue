<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="8">
          <v-alert type="success" dismissible v-model="alertSuccess">ค้นหาห้องสำเร็จ</v-alert>
          <v-alert type="error" dismissible v-model="alertFailed">ไม่พบห้อง!</v-alert>

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
                  <v-btn class="yellow lighten-3" @click="getAllElectricalRegistration">ค้นหา</v-btn>
                </v-col>
              </v-row>
              <template v-if="isData">
                <v-data-table
                  :headers="headers"
                  :items="electricregister"
                  :items-per-page="5"
                  class="elevation-1"
                ></v-data-table>
              </template>
              <v-card v-if="isData">
                <v-card-title>
                  จำนวนเครื่องใช้ไฟฟ้าทั้งหมด
                  <v-spacer></v-spacer>
                  {{ count }}
                </v-card-title>
              </v-card>
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
  },
  data() {
    return {
      isData: false,
      details: null,
      StdNames: [],
      roomNumber: undefined,
      alertSuccess: false,
      alertFailed: false,
      headers: [
        {
          text: "ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "Student", value: "roomBooking.student.fullName" },
        { text: "Date", value: "electricalRegistrationdate" },
        { text: "Electrictype", value: "electricType.name" },
        { text: "Detail", value: "details" }
      ],
      electricregister: [],
      count: 0
    };
  },
  methods: {
    getAllElectricalRegistration() {
      this.electricregister = [];
      this.count = 0;
      api.get("/api/ElectricalRegis").then(l => {
        var tmp = l.data;
        console.log(JSON.parse(JSON.stringify(tmp)));
        for (var i = 0; i < tmp.length; i++) {
          if (tmp[i].roomBooking.rooms.roomId == this.roomNumber) {
            this.electricregister.push(tmp[i]);
            this.count++;
          }
        }
        console.log(JSON.parse(JSON.stringify(this.electricregister)));
        if (this.electricregister.length == 0) {
          this.clearAlert();
          this.alertFailed = true;
          this.isData = false;
        } else {
          this.clearAlert();
          this.alertSuccess = true;
          this.isData = true;
        }
      });
    },

    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
    }
  }
};
</script>
