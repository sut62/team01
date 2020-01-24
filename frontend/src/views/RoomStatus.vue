<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">สถานะห้องพัก</h1>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      <v-col cols="10">
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
        
        { text: "ห้อง", value: "roomBooking.rooms.roomId" },
        { text: "รหัสนักศึกษา", valur: "roomBooking.student.studentId" },
        { text: "ชื่อนักศึกษา", value: "roomBooking.student.fullName" },
        { text: "สาขาวิชา", value: "roomBooking.student.bachelor" },
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
        })
        .catch(e => {
          console.log(e);
        });
    }
  },

  mounted() {
    this.getAllRepair();
  }
};
</script>