<template>
  <v-container>
      <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลการ Confirm เรียบร้อย</v-alert>
      <v-alert type="error" dismissible v-model="alertFailed">กรุณาเลือกข้อมูลให้ครบทุกช่อง!</v-alert>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Package Status</h1>
        <v-data-table
          :headers="headers"
          :items="PackageManagements"
          :items-per-page="5"
          class="elevation-1"
        >
          <template v-slot:item.confirm="{ item }">
            <v-btn class="btn btn-danger mr-1" color="warning" @click="save(item)">confirm</v-btn>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api.js";

export default {
  mounted() {
    this.getAllPackageManagements();
    //this.getAllStaffs();
  },
  data() {
    return {
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
      ]
    };
  },
  methods: {
    getAllPackageManagements() {
      var confirmPackage = [];
      let temp = [];
      console.log(confirmPackage, temp);
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
              console.log("ConfirmPackage loaded.")
              confirmPackage = res.data;
              console.log(confirmPackage);
              for (var i = 0; i < temp.length; i++) {
                for (var j = 0; j < confirmPackage.length ; j++) {
                  if (
                    temp[i].id ==
                    confirmPackage[j].packageManagement.id
                  ) {
                    temp.splice(i, 1);
                  }
                }
                console.log("temp:")
                console.log(JSON.parse(JSON.stringify(temp)))
                 this.PackageManagements = temp;
              }
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
    save(pm) {
      // Debug
      console.log("Selected package:");
      console.log(JSON.parse(JSON.stringify(pm)));
      // Post to server
      api
        .post("/api/confirmPackage/" + pm.id)
        .then(response => {
          console.log(response.data);
          this.clearAlert();
          this.alertSuccess = true;
          this.getAllPackageManagements();
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>
