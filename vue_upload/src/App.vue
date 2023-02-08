<template>
  <el-row>
    <el-col :span="12"><div class="grid-content ep-bg-purple" />
      <el-upload
      ref="uploadRef"
      class="upload-demo"
      action="http://127.0.0.1:8080/uploader/singlefile"
      :auto-upload="false"
    >
      <template #trigger>
        <el-button type="primary">select file</el-button>
      </template>
  
      <el-button class="ml-3" type="success" @click="submitUpload">
        upload to server
      </el-button>
  
      <template #tip>
        <div class="el-upload__tip">
          jpg/png files with a size less than 500kb
        </div>
      </template>
    </el-upload>
    </el-col>
    <el-col :span="12"><div class="grid-content ep-bg-purple-light" />
      <el-upload
      ref="uploadMultiRef"
      class="upload-demo"
      action="#"
      :file-list="fileList"
      :auto-upload="false"
      :http-request="uploadFile"
      :on-success="uploadSuccess"
      :on-remove="removeFileList"
      :on-change="handleChange"
    >
        select file
      <template #tip>
        <div class="el-upload__tip">
          jpg/png files with a size less than 500kb
        </div>
      </template>
    </el-upload>
    <el-button class="ml-3" type="success" @click="submitMultiUpload">
      upload to server
    </el-button>
    </el-col>
  </el-row>

</template>
<script  setup>
import { ref } from 'vue'
const $axios = inject('$axios')


const uploadRef = ref()
const uploadMultiRef = ref()
const fileList = ref([])


const submitUpload = () => {
  console.log("submitUpload");
  uploadRef.value.submit();
}
const handleChange = (event) => {
  console.log(event);

}
const uploadSuccess = (response,uploadFile,uploadFiles) => {
  console.log(response);
  console.log(uploadFile);
  console.log(uploadFiles);
}
const removeFileList = () => {
  console.log("fileList");
}

const submitMultiUpload = () => {
  console.log("submitMultiUpload");
  uploadMultiRef.value.submit();
}
const uploadFile = (options) => {
  console.log(options);
  $axios.post('http://127.0.0.1:8080/uploader/files',options).then((resp) => {    
      console.log(resp.data)
    }).catch((err) => {
      console.log(err)
    })
}


</script>

<style scoped>

</style>
