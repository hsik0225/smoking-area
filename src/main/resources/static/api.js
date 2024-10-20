const SERVER_URL = 'http://localhost:10500';

async function getSmokingAreas(townName) {
    return await fetch(`${SERVER_URL}/v1/areas?townName=${townName}`, {method: 'GET'})
        .then((r) => r.json())
        .then((r) => r.content);
}

export { getSmokingAreas }
