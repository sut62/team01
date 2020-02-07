<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="6">
        <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลสำเร็จ</v-alert>
        <v-alert type="error" dismissible v-model="alertFailed">กรุณาระบุข้อมูลให้ครบและถูกต้อง!</v-alert>
      </v-col>
    </v-row>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Package Management</h1>

        <v-row justify="center" class="pb-0 mb-0">
          <v-col cols="3">
            <v-text-field
              solo
              label="กรอกหมายเลขห้อง"
              v-model="roomNumber"
              @keyup.enter="getSearch"
            />
          </v-col>
          <v-col cols="1">
            <v-btn @click="getSearch" large class="light-green accent-4">Search</v-btn>
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6">
            <v-select
              class="pa-0"
              label="เลือกชื่อนักศึกษา"
              v-model="selectedRoomBooking"
              :items="StdNames"
              item-text="student.fullName"
              item-value="id"
            />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6">
            <v-select
              v-model="selectedPackageType"
              :items="packageTypes"
              item-text="packageType"
              item-value="id"
              label="เลือกประเภทของพัสดุ"
            />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6">
            <v-select
              v-model="selectedStaff"
              :items="staff"
              item-text="name"
              item-value="id"
              label="เลือกชื่อที่ปรึกษาหอพัก"
            />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6" class="pa-0 mx-2">
            <v-text-field
              solo
              label="โปรดระบุสภาพของพัสดุ (สามารถใส่ A-Z, a-z และ white space)"
              height="80px"
              v-model="details"
            />
          </v-col>
        </v-row>

        <div class="text-center">
          <v-btn class="mr-3" color="warning" @click="save">SAVE</v-btn>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api.js";

export default {
  mounted() {
    this.getAllRoomBooking();
    this.getAllPackageTypes();
    this.getAllStaffs();
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

      packageTypes: [],
      selectedPackageType: undefined,
      alertSuccess: false,
      alertFailed: false
    };
  },
  methods: {
    getAllRoomBooking() {
      api
        .get("/api/roombooking/" + this.insRoomNo)
        .then(response => {
          this.StdNames = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log("Error in getStudentName() :" + e);
        });
    },
    getAllPackageTypes() {
      api
        .get("/api/packageType/")
        .then(response => {
          this.packageTypes = response.data;
          console.log("Package Types data have bean loaded.");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log("Error in getAllPackageTypes() : " + e);
        });
    },

    getAllStaffs() {
      api
        .get("/api/staff/")
        .then(response => {
          this.staff = JSON.parse(localStorage.getItem("user"));
          console.log("Staff data have bean loaded.");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log("Error in getAllStaffs() : " + e);
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
    },
    cancle() {
      this.$refs.form.reset();
    },
    checkPattern() {
      var re = new RegExp("[A-Za-z\\s]+");
      if (!re.exec(this.details)) {
        console.log("FALSE");
        return false;
      } else {
        console.log("true");
        return true;
      }
    },
    checkSize() {
      if (this.details.length < 2 || this.details.length > 20) {
        console.log("A");
        return false;
      } else {
        console.log("B");
        return true;
      }
    },
    save() {
      // ครบทุกอัน?
      if (
        !this.selectedRoomBooking ||
        !this.selectedPackageType ||
        !this.selectedStaff ||
        !this.details ||
        !this.checkSize() ||
        !this.checkPattern()
      ) {
        this.clearAlert();
        this.alertFailed = true;
        //alert("กรุณาระบุข้อมูลให้ครบ!");
      } else {
        this.checksave();
      }
    },
    checksave() {
      // เพิ่มเข้ามาใหม่
      let newelepayload = {
        roomBookingId: this.selectedRoomBooking,
        packageTypeId: this.selectedPackageType,
        staffId: this.selectedStaff,
        details: this.details
      };
      console.log(newelepayload);
      api
        .post("/api/packageManagement", newelepayload)
        .then(() => {
          this.clearAlert();
          this.alertSuccess = true;
          //alert("บันทึกข้อมูลสำเร็จ!");
          this.roomNumber = null;
          this.selectedPackageType = null;
          this.selectedRoomBooking = null;
          this.selectedStaff = null;
          this.details = null;
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>
