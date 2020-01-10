<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="6">
          <v-alert type="success" dismissible v-model="alertSuccess">บันทึกสำเร็จ</v-alert>
          <v-alert type="error" dismissible v-model="alertFailed">กรุณาเลือกข้อมูลให้ครบ!</v-alert>
          <v-card class="elevation-12">
            <v-toolbar color="amber" light flat>
              <v-icon>mdi-paper-roll</v-icon>&nbsp;&nbsp;
              <v-toolbar-title>ลงทะเบียนเครื่องใช้ไฟฟ้า</v-toolbar-title>
              <div class="flex-grow-1"></div>
            </v-toolbar>
            <v-card-text>
              <v-select
                v-model="selectedStaff"
                :items="staff"
                item-text="name"
                item-value="id"
                label="เลือกชื่อที่ปรึกษาหอพัก"
              ></v-select>

              <v-row align="center" sm="6">
                <v-col cols="6">
                  <v-text-field
                    v-model="roomNumber"
                    @keyup.enter="getSearch"
                    label="กรอกหมายเลขห้อง"
                  ></v-text-field>
                </v-col>
                <v-col cols="3">
                  <v-btn class="yellow lighten-3" @click="getSearch">search</v-btn>
                </v-col>
              </v-row>

              <v-select
                v-model="selectedRoomBooking"
                :items="StdNames"
                item-text="student.fullName"
                item-value="id"
                label="เลือกชื่อนักศึกษา"
              ></v-select>

              <v-select
                v-model="selectedElectrictype"
                :items="electricType"
                item-text="name"
                item-value="id"
                label="ประเภทเครื่องใช้ไฟฟ้า"
              ></v-select>

              <v-text-field
                v-model="details"
                label="กรอกรายละเอียดเครื่องใช้ไฟฟ้า"
              ></v-text-field>

              <div class="text-Right">
                <v-btn color="warning" @click="Save">Save</v-btn>
              </div>
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

      roomBooking: [],
      selectedRoomBooking: null,

      electricType: [],
      selectedElectrictype: null
    };
  },
  methods: {
    getAllStaffs() {
      this.staff = JSON.parse(localStorage.getItem("user"))
    },

    Save() {
      if (
        !this.selectedStaff ||
        !this.selectedElectrictype ||
        !this.selectedRoomBooking
      ) {
        this.clearAlert();
        this.alertFailed = true;
        // alert("กรุณาเลือกข้อมูลให้ครบ!");
      } else {
        
        this.SavaData();
      }
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

    SavaData() {
      // เพิ่มเข้ามาใหม่
      let newelepayload = {
        roomBookingId: this.selectedRoomBooking,
        electricTypeId: this.selectedElectrictype,
        staffId: this.selectedStaff,
        details: this.details
      };
      // console.log(newelepayload)
      api
        .post("/api/electricRegister", newelepayload)
        .then(() => {
          this.clearAlert();
          this.alertSuccess = true;
          // alert("บันทึกสำเร็จ!");
          this.selectedStaff = null;
          this.selectedElectrictype = null;
          this.selectedRoomBooking = null;
          this.details = null;
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
        })
        .catch(e => {
          console.log("Error in getSearch() :" + e);
        });
    }
  }
};
</script>
