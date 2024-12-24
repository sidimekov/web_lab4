<script>
import Plot from "@/components/Plot.vue";
import PointForm from "@/components/PointForm.vue";

export default {
  name: "MainPage",
  components: {PointForm, Plot},
  data() {
    return {
      points: [],
      currentR: 4
    }
  },
  mounted() {
    // this.fetchPoints();
  },
  methods: {
    async fetchPoints() {
      try {
        const response = await fetch("/api/points");
        this.points = await response.json();
      } catch (error) {
        console.error("Error fetching points: ", error);
      }
    },
    updateR(newR) {
      this.currentR = parseFloat(newR);
    },
    async sendPoint(point) {
      try {
        const response = await fetch('/api/sendPoint', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(point),
        });

        return await response.json();

      } catch (error) {
        console.error('Error sending point:', error);
        throw error;
      }
    },
  }
}
</script>

<template>
  <div class="main-page">
    <Plot :points="points" :current-r="currentR" :send-point="sendPoint" />
    <PointForm :send-point="sendPoint" @updateR="updateR"/>
  </div>
</template>

<style scoped>
.main-page {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
}
</style>