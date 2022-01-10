import axios from 'axios';

export const uploadImg = (file) => {
    const uploadParams = new FormData();
    uploadParams.append('smfile', file);

    const config = {
        method: 'POST',
        url: 'https://sm.ms/api/upload',
        data: uploadParams,
    };
    return axios(config);
}