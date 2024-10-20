import {getSmokingAreas} from "./api.js";

const wrapper = document.querySelector(".wrapper"),
    selectBtn = wrapper.querySelector(".select-btn"),
    options = wrapper.querySelector(".options");

let towns = [
    {
        koreanName: "동대문구",
        englishName: "DONGDAEMUN_GU",
        latitude: 37.571625,
        longitude: 127.0421417
    },
];

function addTown(selectedTown) {
    options.innerHTML = "";
    towns.forEach(town => {
        const li = document.createElement("li");
        if (town.koreanName === selectedTown) {
            li.classList.add("selected");
        }
        li.innerText = town.koreanName;
        li.addEventListener("click", () => updateName(town));
        li.addEventListener("click", () => drawMap(town));
        options.appendChild(li);
    });
}

function updateName(town) {
    addTown(town.koreanName);
    wrapper.classList.remove("active");
    selectBtn.firstElementChild.innerText = town.koreanName;
}

async function drawMap(town) {
    const mapOptions = {
        center: N.LatLng(town.latitude, town.longitude),
        zoom: 16,
        minZoom: 13,
        maxZoom: 18,
    };

    const map = new N.Map('map', mapOptions);

    await drawMarker(map, town);
}

async function drawMarker(map, town) {
    const smokingAreas = await getSmokingAreas(town.englishName)
    for (let i = 0; i < smokingAreas.length; i++) {
        const smokingArea = smokingAreas[i];
        if (smokingArea.latitude === null || smokingArea.longitude === null) {
            continue;
        }

        new N.Marker({
            position: N.LatLng(smokingArea.latitude, smokingArea.longitude),
            map: map,
        });
    }
}

function initialize() {
    const defaultTown = towns[0];
    addTown();
    updateName(defaultTown);
    drawMap(defaultTown);
}

initialize();

selectBtn.addEventListener("click", () => wrapper.classList.toggle("active"));
