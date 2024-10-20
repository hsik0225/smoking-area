const wrapper = document.querySelector(".wrapper"),
    selectBtn = wrapper.querySelector(".select-btn"),
    options = wrapper.querySelector(".options");

let towns = [
    {
        name: "동대문구",
        latitude: 37.571625,
        longitude: 127.0421417
    },
    {
        name: "종로구",
        latitude: 37.57037778,
        longitude: 126.9816417
    }
];

function addTown(selectedTown) {
    console.log('addTown', selectedTown);
    options.innerHTML = "";
    towns.forEach(town => {
        let isSelected = town.name === selectedTown ? "selected" : "";
        let li = `<li onclick="updateName('${town.name}');drawMap(${town.latitude}, ${town.longitude})" class="${isSelected}">${town.name}</li>`;
        options.insertAdjacentHTML("beforeend", li);
    });
}

function updateName(townName) {
    console.log('updateName', townName);
    addTown(townName);
    wrapper.classList.remove("active");
    selectBtn.firstElementChild.innerText = townName;
}

function drawMap(latitude, longitude) {
    console.log('drawMap', latitude, longitude);
    const mapOptions = {
        center: N.LatLng(latitude, longitude),
        zoom: 16,
        minZoom: 15,
        maxZoom: 18,
    };

    new N.Map('map', mapOptions);
}

function initialize() {
    const defaultTown = towns[0]
    addTown()
    updateName(defaultTown.name)
    drawMap(defaultTown.latitude, defaultTown.longitude)
}
initialize();

selectBtn.addEventListener("click", () => wrapper.classList.toggle("active"));
