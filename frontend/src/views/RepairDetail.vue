<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลการแจ้งซ่อมของนักศึกษา</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn color="warning" class="mr-3" @click="back">กลับ</v-btn>
    </div>
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
          text: "ห้อง",
          align: "left",
          sortable: false,
          value: "enrolled.Rooms"
        },
        { text: "ชื่ออุปกรณ์", value: "name.name" },
        { text: "ประเภทอุปกรณ์", value: "type.type" },
        { text: "ปัญหา", value: "list" },
        { text: "เวลาที่แจ้งเข้า", value: "repairDate" }
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