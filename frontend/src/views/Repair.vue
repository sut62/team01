<template>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลการแจ้งยืมเรียบร้อย</v-alert>
             <v-alert type="error" dismissible v-model="alertFailed">กรุณาเลือกข้อมูลให้ครบทุกช่อง!</v-alert>
            <v-card class="elevation-12">
              <v-toolbar color="primary" light flat>
                <v-icon dark>mdi-wrench</v-icon>&nbsp;&nbsp;

                <span class="white--text">
                  <v-toolbar-title>ระบบแจ้งซ่อมหอพัก</v-toolbar-title>
                </span>
                <div class="flex-grow-1"></div>
              </v-toolbar>
              <v-card-text>
                <v-select
                  v-model="selectedStudent"
                  :items="roomBookings"
                  item-text="student.fullName"
                  item-value="id"
                  label="เลือกชื่อนักศึกษา"
                ></v-select>

                <v-select
                  v-model="selectedDeviceType"
                  :items="types"
                  item-text="type"
                  item-value="id"
                  label="เลือกประเภทอุปกรณ์ที่ชำรุด"
                ></v-select>

                <v-select
                  v-model="selectedDeviceName"
                  :items="problems"
                  item-text="name"
                  item-value="id"
                  label="เลือกชื่ออุปกรณ์ที่ชำรุด"
                ></v-select>

                <v-text-field
                  v-model="insertList"
                  :items="titles"
                  item-text="name"
                  item-value="id"
                  label="ระบุอาการ/ปัญหา"
                ></v-text-field>

                <div class="text-center">
                  <v-btn class="mr-3" color="warning" @click="Saveshow"
                    >บันทึก</v-btn
                  >
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
</template>
<script>
import api from "../Api.js";
export default {
  watch: {
    selectedDeviceType: function() {
      this.getAllDeviceNames();
    }
  },
  data() {
    return {
      insertList: undefined,
      selectedStudent: null,
      selectedDeviceType: null,
      selectedDeviceName: null,
      alertSuccess: false,
      alertFailed: false,
      titles: [],
      roomBookings: [],
      types: [],
      problems: []
    };
  },
  mounted() {
    //ประกาศฟังก์ชันที่ต้องการดึงข้อมูลจากหลังบ้านมาแสดงใน combobox แต่ละตัว
    this.getSpecificRoomBookings();
    this.getAllDeviceTypes();
  },
  methods: {
    Resetshow() {
      this.$refs.form.reset();
    },
    Saveshow() {
      //เมือกดปุ่มบันทึก ขณะที่ยังกรอกข้อมูลไม่ครบ ระบบจะแจ้งเตื่อน "กรุณากรอรกข้อมูลให้ครบ"
      if (
        !this.insertList ||
        !this.selectedStudent ||
        !this.selectedDeviceType ||
        !this.selectedDeviceName
      ) {
        //alert("กรุณากรอกข้อมูลให้ครบ!");
        this.clearAlert();
        this.alertFailed = true;
      } else {
        this.SaveRepair();
      }
    },

    SaveRepair() {
      api
        .post(
          "/api/Repair/" +
            this.selectedStudent +
            "/" +
            this.selectedDeviceType +
            "/" +
            this.selectedDeviceName +
            "/" +
            this.insertList
        )
        .then(()=> {
         // alert("บันทึกข้อมูลสำเร็จ!");
          //console.log(JSON.parse(JSON.stringify(response.data)));
          
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
     this.insertList = null;
     this.selectedStudent = null;
     this.selectedDeviceType = null;
     this.selectedDeviceName = null;
    },

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

    getAllDeviceTypes() {
      api
        .get("/api/DeviceTypes")
        .then(response => {
          this.types = response.data;
          console.log("เพิ่มข้อมูล DeviceType แล้ว");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    getAllDeviceNames() {
      api
        .get("/api/DeviceName/" + this.selectedDeviceType)
        .then(response => {
          this.problems = response.data;
          console.log("ดึงข้อมูล Problem สำเร็จ");
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>
