import {createSlice} from '@reduxjs/toolkit';

const modalSlice = createSlice({
    name : "modalSlice",
    initialState : false,
    reducers : {
        toggleModal(state , action){
            return action.payload;
        }
    }
});


export const {toggleModal} = modalSlice.actions;
export default modalSlice.reducer;