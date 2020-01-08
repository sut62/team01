<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="6">
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
                label="เลือกชื่อผู้ดูแล"
              ></v-select>

              <v-row >                
                <v-text-field aria-setsize="5px" 
                  v-model="roomNumber" 
                  @keyup.enter="getSearch" 
                  label="กรอกหมายเลขห้อง"
                ></v-text-field> 
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="getSearch">search</v-btn>
              </v-row>
              
              <v-select
                v-model="selectedRoomBooking"
                :items="roomBooking"
                item-text="name"
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
import api from "../http-common";
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
      api
        .get("/staffs/")
        .then(response => {
          this.staff = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    Save() {
      if (
        !this.selectedStaff ||
        !this.selectedElectrictype ||
        !this.selectedRoomBooking
      ) {
        alert("กรุณาเลือกข้อมูลให้ครบ!");
      } else {
        this.SavaData();
      }
    },

    getAllElectrictype() {
      api
        .get("/electrictypes/")
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
      api
        .post("/ElectricalRegis", newelepayload)
        .then(() => {
          alert("บันทึกสำเร็จ!");
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
        .get("/roombookings/")
        .then(response => {
          this.roomBooking = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    getSearch(){
      api
      .get("/roomBookings/" + this.roomNumber)
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