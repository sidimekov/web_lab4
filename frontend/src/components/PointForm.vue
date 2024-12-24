<script>
export default {
  name: "PointForm",
  data() {
    return {
      xValues: ['-4', '-3', '-2', '-1', '0', '1', '2', '3', '4'],
      rValues: ['-4', '-3', '-2', '-1', '0', '1', '2', '3', '4'],
      selectedX: [],
      selectedR: [4],
      yValue: ''
    };
  },
  props: {
    sendPoint: {
      type: Function,
      required: true
    }
  },
  watch: {
    selectedR() {

    }
  },
  methods: {
    avg(values) {
      if (values.length === 0) return null;
      const sum = values.reduce((acc, a) => acc + parseFloat(a), 0);
      return (sum / values.length).toFixed(2);
    },
    updateR() {
      this.$emit('updateR', this.avg(this.selectedR));
    }
  }
}
</script>

<template>
  <div class="default-panel">
    <form>

      <div class="input-group">
        <div class="val-display">
          <label>X</label>
          <div>{{ avg(this.selectedX) }}</div>
        </div>
        <div class="checkbox-group">
          <label v-for="value in xValues" :key="value">
            <input type="checkbox" :value="value" v-model="selectedX"/>
            {{ value }}
          </label>
        </div>
      </div>

      <div class="input-group">
        <div class="val-display">
          <label for="y-value">Y</label>
        </div>
        <input id="y-value" type="text" v-model="yValue" placeholder="от -3 до 5"/>
      </div>

      <div class="input-group">
        <div class="val-display">
          <label>R</label>
          <div>{{ avg(this.selectedR) }}</div>
        </div>
        <div class="checkbox-group">
          <label v-for="value in rValues" :key="value">
            <input type="checkbox" :value="value" v-model="selectedR" @change="updateR"/>
            {{ value }}
          </label>
        </div>
      </div>

      <input type="submit" value="Тыкнуть" class="btn-primary"/>

    </form>
  </div>
</template>

<style scoped>
.checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.input-group {
  display: flex;
  flex-direction: row;
  gap: 30px;
  justify-content: center;
  align-items: center;
}

label {
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
}

form {
  display: flex;
  flex-direction: column;
  gap: 50px;
}

.val-display {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-weight: bold;
  width: 10px;
}
</style>