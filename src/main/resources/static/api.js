import SERVER_URL from "./config";

async function getSmokingAreas(townName) {
    return await fetch(`${SERVER_URL}/v1/areas?townName=${townName}`, {method: 'GET'})
        .then((r) => r.json())
        .then((r) => r.content);
}

export { getSmokingAreas }
