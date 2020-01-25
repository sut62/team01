<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
            
        <v-alert type="success" dismissible v-model="alertSuccess">ค้นหาสำเร็จ</v-alert>
        <v-alert type="error" dismissible v-model="alertFailed">ไม่พบข้อมูล!</v-alert>
        
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

        <v-card v-if="dataStatus">
            <v-data-table
            :headers="headers"
            :items="lst_BndName"
            :items-per-page="5"
            ></v-data-table>
        </v-card>

        <v-card v-if="!dataStatus">
            <v-data-table
            :headers="headers"
            :items="lstAllEnroll"
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
                { text: "Name", value: "enrolledStudents.student.fullName", align: 'left', sortable: false },
                { text: "Date Enroll", value: "enrollDate", sortable: false },
                { text: "Licnse Plate", value: "licensePlate", sortable: false },
                { text: "Brand Name", value: "brandName", sortable: false },
                { text: "Other Details", value: "otherDetails", sortable: false },
            ],
            lst_BndName: [],
            lstAllEnroll: [],
            bnd_Name: undefined,
            dataStatus: false,
            alertSuccess: false,
            alertFailed: false,
        }
    },
    mounted() {
        this.getEnrolledVehicles();
    },
    methods: {
        searchEnrolledVehicles() {
            api
            .get("/api/enrolledVehicles/searchBrandName=" + this.bnd_Name)
            .then(response  => {
                this.lst_BndName = response.data;
                console.log(JSON.parse(JSON.stringify(response.data)));
                this.clearAlert();
                if(this.lst_BndName.length == 0) {
                    this.alertFailed = true;
                } else {
                    this.alertSuccess = true;
                    this.dataStatus = true;
                }
            })
            .catch(e => {
                console.log("Error in searchEnrolledVehicle() :" + e);
            });
        },
        clearAlert() {
            this.alertSuccess = false;
            this.alertFailed = false;
        },
        getEnrolledVehicles() {
            api
            .get("/api/enrolledVehicles")
            .then(response  => {
                this.lstAllEnroll = response.data;
                console.log(JSON.parse(JSON.stringify(response.data)));
            })
            .catch(e => {
                console.log("Error in searchEnrolledVehicle() :" + e);
            });
        },
    }
}
</script>