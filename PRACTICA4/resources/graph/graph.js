var nodes = new vis.DataSet([
  {id: 1, label: "Quito"},
  {id: 2, label: "Guayaquil"},
  {id: 3, label: "Cuenca"},
  {id: 4, label: "Loja"},
  {id: 5, label: "Ambato"},
  {id: 6, label: "Riobamba"},
  {id: 7, label: "Manta"},
  {id: 8, label: "Portoviejo"},
  {id: 9, label: "Tulcan"},
  {id: 10, label: "Ibarra"},
  {id: 11, label: "Machala"},
  {id: 12, label: "Latacunga"},
  {id: 13, label: "Salinas"},
  {id: 14, label: "Puyo"},
  {id: 15, label: "Gualaceo"},
  {id: 16, label: "Vilcabamba"},
  {id: 17, label: "Cumbaya"},
  {id: 18, label: "Samborondon"},
  {id: 19, label: "Bahia de Caraquez"},
  {id: 20, label: "Cayambe"},
  {id: 21, label: "Daule"},
  {id: 22, label: "San Gabriel"},
  {id: 23, label: "Zamora"},
  {id: 24, label: "Ruminahui"},
  {id: 25, label: "Arenillas"},
  ]);
  var edges = new vis.DataSet([
  {from: 1, to: 2, label: "420 km", arrows: "to", color: "#2B7CE9"},
  {from: 3, to: 4, label: "150 km", arrows: "to", color: "#2B7CE9"},
  {from: 5, to: 6, label: "45 km", arrows: "to", color: "#2B7CE9"},
  {from: 7, to: 8, label: "45 km", arrows: "to", color: "#2B7CE9"},
  {from: 9, to: 10, label: "60 km", arrows: "to", color: "#2B7CE9"},
  {from: 11, to: 4, label: "120 km", arrows: "to", color: "#2B7CE9"},
  {from: 1, to: 12, label: "60 km", arrows: "to", color: "#2B7CE9"},
  {from: 2, to: 13, label: "120 km", arrows: "to", color: "#2B7CE9"},
  {from: 5, to: 14, label: "95 km", arrows: "to", color: "#2B7CE9"},
  {from: 3, to: 15, label: "30 km", arrows: "to", color: "#2B7CE9"},
  {from: 4, to: 16, label: "40 km", arrows: "to", color: "#2B7CE9"},
  {from: 1, to: 17, label: "15 km", arrows: "to", color: "#2B7CE9"},
  {from: 2, to: 18, label: "10 km", arrows: "to", color: "#2B7CE9"},
  {from: 7, to: 19, label: "50 km", arrows: "to", color: "#2B7CE9"},
  {from: 1, to: 20, label: "70 km", arrows: "to", color: "#2B7CE9"},
  {from: 2, to: 21, label: "20 km", arrows: "to", color: "#2B7CE9"},
  {from: 9, to: 22, label: "40 km", arrows: "to", color: "#2B7CE9"},
  {from: 4, to: 23, label: "65 km", arrows: "to", color: "#2B7CE9"},
  {from: 1, to: 24, label: "30 km", arrows: "to", color: "#2B7CE9"},
  {from: 11, to: 25, label: "25 km", arrows: "to", color: "#2B7CE9"},
  ]);
  var container = document.getElementById("mynetwork");
  var data = {
    nodes: nodes,
    edges: edges
  };
  var options = {
    nodes: {
      shape: 'circle',
      size: 30,
      font: {
        size: 16
      },
      borderWidth: 2,
      color: {
        background: '#D2E5FF',
        border: '#2B7CE9'
      }
    },
    edges: {
      width: 2,
      font: {
        size: 12,
        align: 'middle'
      },
      arrows: {
        to: {
          enabled: true,
          scaleFactor: 1
        }
      },
      smooth: {
        type: 'continuous'
      }
    },
    physics: {
      enabled: true,
      stabilization: true
    }
  };
  var network = new vis.Network(container, data, options);