<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        
        <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="6">
            <v-alert type="success" dismissible v-model.lazy="alertSuccess">บันทึกข้อมูลสำเร็จ</v-alert>
            <v-alert type="error" dismissible v-model="alertFailed">กรุณาระบุข้อมูลให้ครบ!</v-alert>
          </v-col>
        </v-row>
        
        <h1 class="display-2 font-weight-bold mb-3">Search Enrolled Vehicle</h1>
        
        <v-row justify="center" class="pb-0 mb-0">
            <v-col cols="3">
            <v-text-field solo 
            label="กรอกยี่ห้อยานพาหนะ"
            v-model="bnd_Name"
            @keyup.enter="searchEnrolledVehicles"/>
            </v-col>
            <v-col cols="1">
                <v-btn @click="searchEnrolledVehicles" large class="light-blue darken-1">Search</v-btn>
            </v-col>
        </v-row>

        <v-card>
            <v-data-table
            :headers="headers"
            :items="lst_BndName"
            :items-per-page="5"
            ></v-data-table>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
import api from "../Api.js";
export default {
    data() {
        return {
            headers: [
                { text: "Name", value: "enrolledStudents.student.fullName", align: 'left'},
                { text: "Date Enroll", value: "enrollDate" },
                { text: "Licnse Plate", value: "licensePlate" },
                { text: "Brand Name", value: "brandName" },
                { text: "Other Details", value: "otherDetails" },
            ],
            lst_BndName: [],
            items: [],
            bnd_Name: undefined,
            alertSuccess: false,
            alertFailed: false,
        }
    },
    methods: {
        searchEnrolledVehicles() {
            api
            .get("/api/enrolledVehicles/searchBrandName=" + this.bnd_Name)
            .then(response  => {
                this.lst_BndName = response.data;
                console.log(JSON.parse(JSON.stringify(response.data)));
            })
            .catch(e => {
                console.log("Error in searchEnrolledVehicle() :" + e);
            });
        }
    }
}
</script>