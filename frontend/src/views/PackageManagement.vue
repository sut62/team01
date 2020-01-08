<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="6">
          <v-card class="elevation-12">
            <v-toolbar color="amber" light flat>
              <v-icon>mdi-package</v-icon>&nbsp;&nbsp;
              <v-toolbar-title>จัดการพัสดุ</v-toolbar-title>
              <div class="flex-grow-1"></div>
            </v-toolbar>
            <v-card-text>
              <v-row justify="center" class="pb-0 mb-0">
                <v-col cols="3">
                  <v-text-field
                    solo
                    label="กรอกหมายเลขห้อง"
                    v-model="insRoomNo"
                    @keyup.enter="getStudentName"
                  />
                </v-col>
                <v-col cols="1">
                  <v-btn @click="getStudentName" large class="light-green accent-4">Search</v-btn>
                </v-col>
              </v-row>

              <v-select
                v-model="selectedRoomBooking"
                :items="roomBooking"
                item-text="name"
                item-value="id"
                label="เลือกชื่อนักศึกษา"
              ></v-select>

              <v-select
                v-model="selectedPackageType"
                :items="packageTypes"
                item-text="packageType"
                item-value="id"
                label="เลือกประเภทของพัสดุ"
              ></v-select>

              <v-select
                v-model="selectedStaff"
                :items="staff"
                item-text="name"
                item-value="id"
                label="เลือกชื่อผู้ดูแล"
              ></v-select>

              <v-text-field v-model="details" label="โปรดระบุสภาพของพัสดุ"></v-text-field>

              <div class="text-center">
                <v-btn class="mr-3" color="warning" @click="save">บันทึก</v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

  <script>
import api from "../http-common";

export default {
  mounted() {
    this.getAllRoomBooking();
    this.getAllPackageTypes();
    this.getAllStaffs();
  },
  data() {
    return {
      insRoomNo: undefined,
      staff: [],
      selectedStaff: null,
      details: null,
      roomNumber: undefined,

      roomBooking: [],
      selectedRoomBooking: null,

      packageTypes: [],
      selectedPackageType: null
    };
  },
  methods: {
    getAllRoomBooking() {
      api
        .get("/api/roomBooking/")
        .then(response => {
          this.roomBookings = response.data;
          console.log("Room Booking data have bean loaded.");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log("Error in getAllStaffs() : " + e);
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
          this.staff = response.data;
          console.log("Staff data have bean loaded.");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log("Error in getAllStaffs() : " + e);
        });
    },
    getStudentName() {
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
    cancle() {
      this.$refs.form.reset();
    },
    save() {
      if (
        !this.details ||
        !this.selectedRoomBooking ||
        !this.selectedPackageType ||
        !this.selectedStaff
      ) {
        alert("กรุณาระบุข้อมูลให้ครบ!");
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
      api
        .post("/packageManagement", newelepayload)
        .then(() => {
          alert("บันทึกข้อมูลสำเร็จ!");
          this.selectedStaff = null;
          this.selectedPackageType = null;
          this.selectedRoomBooking = null;
          this.details = null;
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>