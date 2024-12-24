<script>
import * as d3 from "d3";

export default {
  name: "Plot",
  props: {
    points: {
      type: Object
    },
    currentR: {
      type: Number
    },
    sendPoint: {
      type: Function,
      required: true
    }
  },
  mounted() {
    this.drawPlot();
    // this.addPoints();
  },
  watch: {
    currentR() {
      d3.select("#plot svg").remove();
      this.drawPlot();
      // this.addPoints();
    },
    points() {
      this.drawPoints(this.points);
    }
  },
  methods: {
    async drawPoints(points) {
      points.forEach((point) => {
        this.drawPoint(point.x, point.y, point.r, point.isIn);
      });
    },
    drawPoint(x, y, r, isIn) {
      const currentR = parseFloat(this.currentR) || 4;
      console.log(currentR, r);
      if (r === currentR) {
        const width = 400;
        const height = 400;
        const padding = 50;
        const xScale = d3.scaleLinear().domain([-4, 4]).range([padding, width - padding]);
        const yScale = d3.scaleLinear().domain([-4, 4]).range([height - padding, padding]);

        const svg = d3.select("#plot svg");

        svg
            .append("circle")
            .attr("cx", xScale(x))
            .attr("cy", yScale(y))
            .attr("r", 5)
            .attr("fill", isIn ? "blue" : "red")
            .attr("opacity", 0.8);
      }
    },
    drawPlot() {
      const width = 400;
      const height = 400;
      const pad = 50;
      const r = parseFloat(this.currentR) || 4;
      console.log(this.currentR)
      const svg = d3
          .select("#plot")
          .append("svg")
          .attr("width", width)
          .attr("height", height)
          .style("background-color", "none");

      const xScale = d3.scaleLinear().domain([-4, 4]).range([pad, width - pad])
      const yScale = d3.scaleLinear().domain([-4, 4]).range([height - pad, pad])

      const axisColor = "white";
      const gradient = svg
          .append("defs")
          .append("linearGradient")
          .attr("id", "neon-gradient")
          .attr("x1", "0%")
          .attr("x2", "100%")
          .attr("y1", "0%")
          .attr("y2", "100%");

      // точки цветового перехода
      gradient
          .append("stop")
          .attr("offset", "0%")
          .attr("stop-color", "#33ccff");

      gradient
          .append("stop")
          .attr("offset", "100%")
          .attr("stop-color", "#ff99cc");

      // ось по X
      svg
          .append("g")
          .attr("transform", `translate(0, ${height/2})`)
          .call(d3.axisBottom(xScale).ticks(10).tickSizeOuter(0).tickFormat(d3.format(".1f")))
          .attr("color", axisColor);

      // ось по Y
      svg
          .append("g")
          .attr("transform", `translate(${width / 2},0)`)
          .call(d3.axisLeft(yScale).ticks(10).tickSizeOuter(0).tickFormat(d3.format(".1f")))
          .attr("color", axisColor);

      if (this.currentR !== 0 && !isNaN(this.currentR)) {
        const pathData = d3
            .path();
        pathData
            .moveTo(xScale(0), yScale(0));
        pathData
            .arc(xScale(0), yScale(0), (Math.sign(r)) * (xScale(r) - xScale(0)), Math.PI + Math.PI / 2 * (-Math.sign(r)), Math.PI * 3 / 2 + Math.PI / 2 * (-Math.sign(r)));
        pathData
            .lineTo(xScale(0), yScale(0));
        pathData
            .lineTo(xScale(0), yScale(r));
        pathData
            .lineTo(xScale(r), yScale(r));
        pathData
            .lineTo(xScale(r), yScale(0));
        pathData
            .lineTo(xScale(r / 2), yScale(0));
        pathData
            .lineTo(xScale(0), yScale(-r));
        pathData
            .lineTo(xScale(0), yScale(0));
        pathData
            .closePath();

        svg
            .append("path")
            .attr("d", pathData)
            .attr("fill", "url(#neon-gradient)")
            .attr("opacity", 0.7);
      }

      // чтобы не обрезался svg
      svg.attr("viewBox", `0 0 ${width} ${height}`).attr("preserveAspectRatio", "xMidYMid meet");

      svg
          .on("click", (e) => {
            const coords = d3.pointer(e);
            const x = ((coords[0] - 200) / 37.5).toFixed(2);
            const y = -((coords[1] - 200) / 37.5).toFixed(2);
            this.drawPoint(x,y,r,true);
            console.log({x,y,r});
          })
    }
  }

}
</script>

<template>
  <div class="default-panel">
    <div id="plot"></div>
  </div>
</template>

<style scoped>
#plot {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
  border-radius: 10px;
  overflow: visible
}
</style>