import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

// Async thunk for adding a task
export const addTask = createAsyncThunk(
  "tasks/addTask",
  async (taskData, thunkAPI) => {
   
    try {
      const res = await fetch("http://localhost:8080/api/user", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(taskData),
      });
      if (!res.ok) throw new Error("Failed to add task");
      return await res.json();
    } catch (err) {
      return thunkAPI.rejectWithValue(err.message);
    }
  }
);

const todoSlice = createSlice({
  name: "todoSlice",
  initialState: {
    items: [
    
    
    ],
    loading: false,
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addTask.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(addTask.fulfilled, (state, action) => {
        console.log(action.payload);
        state.loading = false;
        state.items.push(action.payload);
        state.error = null;
      })
      .addCase(addTask.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export default todoSlice.reducer;
