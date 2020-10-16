import Vue from 'vue'
import Router from 'vue-router'
// import GalleryMenu from './components/gallery/GalleryMenu'
import Landing from './components/Landing'

import SnotelProject from './components/snotel/SnotelProject'
import LocationList from './components/snotel/LocationList'
// import SnotelD3 from './components/snotel/SnotelD3'
import LocationDetail from './components/snotel/LocationDetail'


Vue.use(Router)

export default new Router({
  mode:'history',
  base: process.env.BASE_URL,
  routes: [

    {
      path: '/',
      component: Landing
    },

    {
      path: '/landing',
      component: Landing
    },

    {
      path:'/snotel',
      component: SnotelProject
    },

    {
      path:'/snoteldata',
      component: LocationList
    },
    { path: '/location/:id', 
        name:'location_detail',
        component: LocationDetail,
        props: true
    },

  ]
})
