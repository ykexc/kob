<template>
  <ContentField h="85vh">
    <div class="card-body">
      <table class="table table-hover" style="text-align: center;">
        <thead>
        <tr>
          <th>A</th>
          <th>B</th>
          <th>对战结果</th>
          <th>对战时间</th>
          <th>对战操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="record in records" :key="record.record.id">
          <td>
            <img :src="record.a_photo" alt="" class="record_user_photo">
            &nbsp;
            <span class="record_username">{{ record.a_username }}</span>
          </td>
          <td>
            <img :src="record.b_photo" alt="" class="record_user_photo">
            &nbsp;
            <span class="record_username">{{ record.b_username }}</span>
          </td>
          <td>{{ record.result }}</td>
          <td>{{ record.record.createTime }}</td>
          <td>
            <button @click="open_record_content(record.record.id)" type="button" class="btn btn-secondary">watch the
              record
            </button>
          </td>
        </tr>
        </tbody>
      </table>
      <nav aria-label="...">
        <ul class="pagination" style="float: right;">
          <li class="page-item">
            <a @click="click_page(-2)" class="page-link" href="#">前一页</a>
          </li>
          <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number">
            <a @click="click_page(page.number)" class="page-link" href="#">{{page.number}}</a>
          </li>
          <li class="page-item">
            <a @click="click_page(-1)" class="page-link" href="#">后一页</a>
          </li>
        </ul>
      </nav>
    </div>
  </ContentField>
</template>


<script>
import ContentField from '@/components/ContentField.vue'
import {useStore} from "vuex";
import $ from 'jquery'
import {ref} from "vue";
import router from "@/router";

export default {
  components: {
    ContentField,
  },
  setup() {
    const store = useStore()
    let records = ref([])
    let current_page = 1
    let total_record = 0
    let pages = ref([])

    const update_pages = () => {
      let new_pages = []
      let max_pages = parseInt(Math.ceil(total_record / 10))
      for (let i = current_page - 2; i <= current_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({
            number: i,
            is_active: i === current_page ? "active" : ""
          })
        }
      }
      pages.value = new_pages
    }

    const click_page = page => {
      if (page === -2) page = current_page - 1
      else if (page === -1) page = current_page + 1
      let max_page = parseInt(Math.ceil(total_record / 10))
      if (page >= 1 && page <= max_page) pull_page(page)
    }


    const pull_page = page => {
      current_page = page
      $.ajax({
        url: "https://ykexc.work/api/record/getlist/",
        type: "GET",
        data: {
          page: page
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token
        },
        success(resp) {
          records.value = resp.records
          total_record = resp.records_count
          update_pages()
        }
      })
    }

    const stringTo2D = map => {
      let g = []
      for (let i = 0, k = 0; i < 13; i++) {
        let line = []
        for (let j = 0; j < 14; j++, k++) {
          if (map[k] === '0') {
            line.push(0)
          } else {
            line.push(1)
          }
        }
        g.push(line)
      }
      return g
    }


    const open_record_content = recordId => {
      for (const record of records.value) {
        if (record.record.id === recordId) {
          store.commit("updateIsRecord", true)
          store.commit("updateGame", {
            map: stringTo2D(record.record.map),
            a_id: record.record.aid,
            a_sx: record.record.asx,
            a_sy: record.record.asy,
            b_id: record.record.bid,
            b_sx: record.record.bsx,
            b_sy: record.record.bsy
          });
          store.commit("updateSteps", {
            a_steps: record.record.asteps,
            b_steps: record.record.bsteps
          });
          store.commit("updateRecordLoser", record.record.loser)
          router.push({
            name: "record_content",
            params: {
              recordId: recordId
            }
          })


          break
        }
      }
    }

    pull_page(current_page)
    return {
      records,
      open_record_content,
      pages,
      click_page
    }
  }
}
</script>


<style scoped>
img.record_user_photo {
  width: 4vh;
  border-radius: 50%;
}
</style>