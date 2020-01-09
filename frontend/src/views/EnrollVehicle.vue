<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Enroll Vehicle</h1>

        <v-row justify="center" class="pb-0 mb-0">
          <v-col cols="3">
            <v-text-field solo 
            label="กรอกหมายเลขห้อง"
            v-model="insRoomNo"
            @keyup.enter="getStudentName"/>
          </v-col>
          <v-col cols="1">
            <v-btn @click="getStudentName" large class="light-green accent-4">Search</v-btn>
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6">
            <v-select class="pa-0 ma-0"
            label="เลือกชื่อที่ปรึกษาหอพัก"
            v-model="selectedStaffName"
            :items="StaffName"
            item-text="name"
            item-value="id"
            />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6">
            <v-select class="pa-0"
            label="เลือกชื่อนักศึกษา"
            v-model="selectedStdName"
            :items="StdNames"
            item-text="student.fullName"
            item-value="id"
            />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6">
            <v-select class="pa-0 ma-0"
            label="เลือกประเภทยานพาหนะ"
            v-model="selectedVehicleType"
            :items="VehicleTypes"
            item-text="type"
            item-value="id"
            />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="3" class="pa-1 mr-1">
            <v-text-field solo 
            label="กรอกป้ายทะเบียน"
            v-model="insLcPlate"/>
          </v-col>
          <v-col cols="3" class="pa-1 ml-1">
            <v-text-field solo 
            label="กรอกยี่ห้อยานพาหนะ" 
            v-model="insVhcBrand"/>
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6" class="pa-0 mx-2">
            <v-text-field solo
            label="รายละเอียดอื่นๆ" 
            height="80px"
            v-model="insOtherDetails" 
            />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="6" class="pa-0 mx-2">
            <v-btn @click="enrollVehicle" class="light-blue accent-4">ENROLL</v-btn>
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api";
export default {
  data() {
    return {
      selectedStaffName: undefined,
      selectedStdName: undefined,
      selectedVehicleType: undefined,
      insRoomNo: undefined,
      insLcPlate: undefined,
      insVhcBrand: undefined,
      insOtherDetails: undefined,
      StaffName: [],
      StdNames: [],
      VehicleTypes: [],
    }
  },

  mounted() {
    this.getStaffName();
    this.getVehicleTypes();
    // this.checkValiable();
  },

  methods: {
    // getStaffName() {
    //   api
    //   .get("/api/staff")
    //   .then(response => {
    //     this.Staffs = response.data;
    //     console.log(JSON.parse(JSON.stringify(response.data)));
    //   })
    //   .catch(e => {
    //     console.log("Error in getStaffName() :" + e);
    //   });
    // },
    getStaffName() {
      this.StaffName = JSON.parse(localStorage.getItem("user"));
      alert(this.StaffName);
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
    getVehicleTypes() {
      api
      .get("/api/vehicleTypes")
      .then(response => {
        this.VehicleTypes = response.data;
        console.log(JSON.parse(JSON.stringify(response.data)));
      })
      .catch(e => {
        console.log("Error in getVehicleTypes() :" + e);
      });
    },
    enrollVehicle() {
      
      if(this.checkValiable()){
        api
        .post(
          "/enrollVehicle/" +
          this.selectedStaffName + "/" + 
          this.selectedVehicleType + "/" +
          this.selectedStdName + "/" +
          this.insLcPlate + "/" +
          this.insVhcBrand + "/" +
          this.insOtherDetails
        )
        .then(response => {
          console.log(response.data);
          location.reload();
          alert("บันทึกสำเร็จ");
        })
        .catch(e => {
          console.log(e);
        });
      } else {
        alert("กรุณากรอกข้อมูลให้ครบ")
      }
    },
    checkValiable() {
      if(
      !this.selectedStaffName ||
      !this.selectedStdName ||
      !this.selectedVehicleType ||
      !this.insLcPlate || 
      !this.insVhcBrand ||
      !this.insOtherDetails) {
        return false;
      } else {
        return true;
      }
    }
  },
}
</script>
