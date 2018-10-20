<?php

use Illuminate\Database\Seeder;
use Faker\Generator as Faker;
use App\User;
class UsersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        foreach(range(1,50) as $index)
            {
                User::create($this->data());
            }
    }

    private function data()
    {
        $faker = Faker::create();
        return [
            'name'           => $faker->name,
            'email'          => $faker->unique()->safeEmail,
            'avatar'         => $faker->imageUrl,
            'mobile'         => $faker->e164PhoneNumber,
            'password'       => '$2y$10$TKh8H1.PfQx37YgCzwiKb.KjNyWgaHb9cbcoQgdIVFlYg7B77UdFm', // secret
            'remember_token' => str_random(10),
        ];
    }
}
