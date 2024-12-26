<script>
export default {
  name: "PointTable",
  props: {
    points: {
      type: Array,
      required: true
    }
  }
}
</script>

<template>
  <div class="default-panel">
    <table>
      <thead>
      <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Попал?</th>
        <th>Дата</th>
        <th>Время выполнения</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(point, index) in points" :key="index">
        <td>{{ point.x }}</td>
        <td>{{ point.y }}</td>
        <td>{{ point.r }}</td>
        <td>{{ point.in ? 'Да' : 'Нет' }}</td>
        <td>{{ point.point_date }}</td>
        <td>{{ point.execTime }} ms</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

.default-panel {
  width: 100%;
  border-collapse: collapse;
  min-width: 720px;
}

table {
  width: 100%;
  border-collapse: collapse;
  color: #fff;
}

th {
  padding: 15px;
  background: #6a49ff;
  color: #fff;
  text-transform: uppercase;
  font-weight: bold;
  text-align: center;
  border: 1px solid #333;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
  font-size: 14px;
}

td {
  padding: 12px;
  text-align: center;
  border: 1px solid #333;
  background-color: #353750;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
}

tr:nth-child(even) {
  background-color: #2e2f3c;
}

tr:hover {
  background: linear-gradient(145deg, #00d2ff, #6a49ff);
  color: #fff;
}
</style>