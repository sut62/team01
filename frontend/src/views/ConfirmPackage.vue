<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="6">
        <div class="btn btn-danger mr-1">
          <v-bottom-sheet v-model="sheet" persistent>
            <v-row align="right" justify="center">
              <v-sheet class="text-center" height="150px" width="600px">
                <v-btn class="ma-2" color="error" @click="sheet = !sheet; cancle()">cancel</v-btn>
                <v-btn class="ma-2" color="success" @click="sheet = !sheet; save()">Save</v-btn>

                <div class="py-3">กรุณากดปุ่ม save เพื่อยืนยันการรับพัสดุ</div>
              </v-sheet>
            </v-row>
          </v-bottom-sheet>
        </div>
        <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลการ Confirm เรียบร้อย</v-alert>
        <v-alert type="yellow darken-2" dismissible v-model="alertFailed">ไม่ได้ทำการบันทึกข้อมูล</v-alert>
      </v-col>
    </v-row>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <br />
        <v-row align="left" justify="left">
          <h1 class="display-1 font-weight-bold ma-2">Confirm Package</h1>
        </v-row>
        <v-data-table
          :headers="headers"
          :items="PackageManagements"
          :items-per-page="5"
          class="elevation-1"
        >
          <template v-slot:item.confirm="{ item }">
            <v-btn class="ma-2" color="warning" @click="sheet = !sheet; handleSave(item)">confirm</v-btn>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>

    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <br />
        <v-row align="left" justify="left">
          <h1 class="display-1 font-weight-bold ma-2">Confirmed</h1>
        </v-row>
        <v-data-table
          :headers="confirmedHeaders"
          :items="confirmedPackages"
          :items-per-page="5"
          class="elevation-1"
        ></v-data-table>
      </v-flex>
    </v-layout>
  </v-container>
</template>

        

<script>
import api from "../Api.js";

export default {
  mounted() {
    this.getSignedInStaff();
    this.getAllPackageManagements();
  },
  data() {
    return {
      selectedStaff: null,
      selectedPackageId: null,
      confirmedPackages: [],
      sheet: false,
      alertSuccess: false,
      alertFailed: false,
      selectedPackage: null,
      PackageManagements: [],
      headers: [
        {
          text: "ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "Date", value: "packageDate" },
        { text: "Room", value: "roomBooking.rooms.roomId" },
        { text: "Student ID", value: "roomBooking.student.studentId" },
        { text: "Student Name", value: "roomBooking.student.fullName" },
        { text: "Package Type", value: "packageType.packageType" },
        { text: "Detail", value: "details" },
        { text: "Confirm", value: "confirm", sortable: false }
      ],

      confirmedHeaders: [
        {
          text: "ID",
          align: "left",
          value: "id"
        },
        { text: "Confirm Date", value: "confirmDate" },
        { text: "Room", value: "packageManagement.roomBooking.rooms.roomId" },
        {
          text: "Student ID",
          value: "packageManagement.roomBooking.student.studentId"
        },
        {
          text: "Student Name",
          value: "packageManagement.roomBooking.student.fullName"
        },
        {
          text: "Package Type",
          value: "packageManagement.packageType.packageType"
        },
        { text: "Detail", value: "packageManagement.details" }
      ]
    };
  },
  methods: {
    handleSave(p) {
      this.selectedPackageId = p.id;
      console.log(JSON.parse(JSON.stringify(p)));
    },
    getAllPackageManagements() {
      var confirmP = [];
      let temp = [];
      console.log(confirmP, temp);
      api
        .get("/api/packageManagement/")
        .then(response => {
          // this.PackageManagements = response.data;
          console.log("Package Management data have bean loaded.");
          console.log(JSON.parse(JSON.stringify(response.data)));
          temp = response.data;
          api
            .get("/api/confirmPackage")
            .then(res => {
              console.log("ConfirmPackage loaded.");
              confirmP = res.data;
              console.log(confirmP);
              // หาพัสดุที่ยังไม่รับ
              for (var i = 0; i < temp.length; i++) {
                for (var j = 0; j < confirmP.length; j++) {
                  if (temp[i].id == confirmP[j].packageManagement.id) {
                    temp.splice(i, 1);
                  }
                }
              }
              console.log("temp:");
              console.log(JSON.parse(JSON.stringify(temp)));
              this.PackageManagements = temp;
              this.confirmedPackages = res.data;
            })
            .catch(e => {
              console.log(e);
            });
        })
        .catch(e => {
          console.log("Error in getAllPackageManagements() : " + e);
        });
    },
    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
    },
    cancle() {
      this.clearAlert();
      this.alertFailed = true;
    },
    save() {
      // Post to server
      let body = {
        packageManagementId: this.selectedPackageId,
        staffId: this.selectedStaff
      };
      api
        .post("/api/confirmPackage2", body)
        .then(response => {
          console.log(response.data);
          this.clearAlert();
          this.alertSuccess = true;
          this.getAllPackageManagements();
          this.getAllConfirmPackages();
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAllConfirmPackages() {
      this.confirmedPackages = [];
      api
        .get("/api/confirmPackage")
        .then(response => {
          this.confirmedPackages = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log("Error in getConfirmPackages() :" + e);
        });
    },
    getSignedInStaff() {
      var user = JSON.parse(localStorage.getItem("user"));
      this.selectedStaff = user.id;
    }
  }
};
</script>
