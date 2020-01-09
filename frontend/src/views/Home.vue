<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="8">
      <template>
        <v-card class="mx-auto" max-width="600">
          <v-img
            class="white--text align-end"
            height="200px"
            src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          >
            <template v-slot:placeholder>
              <v-row class="fill-height ma-0" align="center" justify="center">
                <v-progress-circular
                  indeterminate
                  color="grey lighten-5"
                ></v-progress-circular>
              </v-row>
            </template>
            <v-card-title>SUT Dormitory Home</v-card-title>
          </v-img>

          <v-card-subtitle class="pb-0">Detials</v-card-subtitle>

          <v-card-text class="text--primary">
            <div>Whitehaven Beach</div>

            <div>Whitsunday Island, Whitsunday Islands</div>
          </v-card-text>

          <v-card-actions>
            <v-btn color="orange" text>
              Share
            </v-btn>

            <v-btn color="orange" text>
              Explore
            </v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "home",
  data() {
    return {};
  },
  methods: {},
  mounted() {}
};
</script>
