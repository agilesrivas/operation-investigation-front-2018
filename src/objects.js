import IndexView from '@/views/IndexView'
import CategoryView from '@/views/CategoryView'
import ProductView from '@/views/ProductView'
import ProviderView from '@/views/ProviderView'
import SalesView from '@/views/SalesView'
import ModelsView from '@/views/ModelsView'
import ConfigView from '@/views/ConfigView'

const routes = [
  {
    path: '/',
    name: 'index',
    component: IndexView
  },
  {
    path: '/category',
    name: 'category',
    component: CategoryView
  },
  {
    path: '/models',
    name: 'models',
    component: ModelsView
  },
  {
    path: '/product',
    name: 'product',
    component: ProductView
  },
  {
    path: '/provider',
    name: 'provider',
    component: ProviderView
  },
  {
    path: '/sales',
    name: 'sales',
    component: SalesView
  },
  {
    path: '/config',
    name: 'config',
    component: ConfigView
  }
]

export {
  routes
}