<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <v-alert type="success" dismissible v-model="alertSuccess">พบข้อมูลการแจ้งซ่อม</v-alert>
       <v-alert type="error" dismissible v-model="alertFailed">{{alertmsg}}</v-alert>
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลการแจ้งซ่อมของนักศึกษา</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="11">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-3"></v-data-table>
      </v-col>
    </v-row>


  </v-container>
</template>

<script>
import api from "../Api.js";
export default {
  name: "RepairDetail",
  data() {
    return {
      headers: [
        {
          text:"ลำดับ",
          align: "center",
          sortable: false,
          value: "id"
        },
        { text: "ผู้แจ้ง", value: "enrolled.student.fullName" },
        { text: "ห้อง", sortable: false, value: "enrolled.rooms.roomId" },
        { text: "ชื่ออุปกรณ์ที่ชำรุด", value: "name.name" },
        { text: "ประเภทอุปกรณ์ที่ชำรุด", value: "type.type" },
        { text: "ปัญหา", value: "list" },
        { text: "เบอร์ติดต่อ", value: "tel" },
        { text: "วันที่แจ้ง", value: "repairDate" }
      ],
      items: []
    };
  },

  methods: {
    back() {
      this.$router.push("/home");
    },
    getAllRepair() {
      api
        .get("api/Repair")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
          if(this.items.length == 0){
          this.clearAlert();
          this.alertmsg = "ไม่พบข้อมูลการแจ้งซ่อม";
          this.alertFailed = true;

          }
         else{
           this.clearAlert();
         this.alertSuccess = true;
         }
        })
        .catch(e => {
          console.log(e);
        });
    },
    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
       
    }
  },

  mounted() {
    this.getAllRepair();
  }
};
</script>